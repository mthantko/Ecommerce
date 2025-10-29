package com.ecom.app.service;

import com.ecom.app.dto.AddressDTO;
import com.ecom.app.dto.UserReqDTO;
import com.ecom.app.dto.UserRespDTO;
import com.ecom.app.entity.Address;
import com.ecom.app.entity.User;
import com.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void addUser(UserReqDTO userReqDTO) {
       // user.setId(nextId++);
        User user = new User();
        updateUserFromReq(user, userReqDTO);
        userRepository.save(user);
    }

    private void updateUserFromReq(User user, UserReqDTO userReqDTO) {
        user.setFirstName(userReqDTO.getFirstName());
        user.setLastName(userReqDTO.getLastName());
        user.setEmail(userReqDTO.getEmail());
        user.setPhoneNumber(String.valueOf(userReqDTO.getPhoneNumber()));
        if(userReqDTO.getAddress() != null) {
            Address address = new Address();
            address.setId(userReqDTO.getAddress().getId());
            address.setState(userReqDTO.getAddress().getState());
            address.setCity(userReqDTO.getAddress().getCity());
            address.setStreet(userReqDTO.getAddress().getStreet());
            address.setCountry(userReqDTO.getAddress().getCountry());
            address.setZipcode(userReqDTO.getAddress().getZipcode());
            user.setAddress(address);
        }
    }

    public Optional<UserRespDTO> fetchUserById(Long id) {
        return userRepository.findById(id).map(this::mapToUserRespDTO);
    }

    public boolean updateUser(Long id, UserReqDTO updateUserReqDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateUserFromReq(existingUser, updateUserReqDTO);
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
        userResp.setRole(user.getUserRole());
        userResp.setPhoneNumber(user.getPhoneNumber());

        if(user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(user.getAddress().getId());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResp.setAddress(addressDTO);
        }

        return userResp;

    }
}
