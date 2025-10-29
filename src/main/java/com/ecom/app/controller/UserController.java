package com.ecom.app.controller;

import com.ecom.app.service.UserService;
import com.ecom.app.dto.UserReqDTO;
import com.ecom.app.dto.UserRespDTO;
import com.ecom.app.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService  userService;



    @GetMapping("/{id}")
    //@RequestMapping(value = "/api/users/{id}",  method = RequestMethod.GET)
    public ResponseEntity<UserRespDTO> getUserById(@PathVariable Long id) {
        return userService.fetchUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<UserRespDTO>> findAll() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserReqDTO userReqDTO) {
        userService.addUser(userReqDTO);
        return ResponseEntity.ok("Add User Successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserReqDTO updateUserReqDTO) {
        boolean updated = userService.updateUser(id, updateUserReqDTO);
        if (updated) {
            return ResponseEntity.ok("Update User Successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
