package com.ecom.app.Service;

import com.ecom.app.entity.User;
import com.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //private List<User> userList =  new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
       // user.setId(nextId++);
        userRepository.save(user);
    }

    public Optional<User> fetchUserById(Long id) {
                return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User userUpdate) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(userUpdate.getFirstName());
                    existingUser.setLastName(userUpdate.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);


    }
}
