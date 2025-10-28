package com.ecom.app.Service;

import com.ecom.app.dto.AddressDTO;
import com.ecom.app.dto.UserReqDTO;
import com.ecom.app.dto.UserRespDTO;
import com.ecom.app.entity.User;
import com.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    //private List<User> userList =  new ArrayList<>();
    //private Long nextId = 1L;

    public List<UserRespDTO> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserRespDTO)
                .collect(Collectors.toList());
    }

    public void addUser(User user) {
       // user.setId(nextId++);
        userRepository.save(user);
    }

    public Optional<UserRespDTO> fetchUserById(Long id) {
        return userRepository.findById(id).map(this::mapToUserRespDTO);
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

    private UserRespDTO mapToUserRespDTO(User user) {
        UserRespDTO userResp = new UserRespDTO();
        userResp.setId(String.valueOf(user.getId()));
        userResp.setFirstName(user.getFirstName());
        userResp.setLastName(user.getLastName());
        userResp.setEmail(user.getEmail());
        userResp.setPhoneNumber(user.getPhoneNumber());

        if(user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(user.getAddress().getId());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResp.setAddress(addressDTO);
        }

        return userResp;

    }
}
