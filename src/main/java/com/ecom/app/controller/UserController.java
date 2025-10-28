package com.ecom.app.controller;

import com.ecom.app.Service.UserService;
import com.ecom.app.dto.UserReqDTO;
import com.ecom.app.dto.UserRespDTO;
import com.ecom.app.entity.User;
import com.ecom.app.repository.UserRepository;
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
    private final UserReqDTO userReqDTO;
    private final UserRespDTO userRespDTO;


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
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("Add User Successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody User updatedUser) {
        boolean updated = userService.updateUser(id, updatedUser);
        if (updated) {
            return ResponseEntity.ok("Update User Successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
