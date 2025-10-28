package com.ecom.app.dto;

import com.ecom.app.role.UserRole;
import lombok.Data;

@Data
public class UserRespDTO {
    private String id;
    private String firstName;
    private String lastName;
    private  String email;
    private String phoneNumber;
    private UserRole role;
    private AddressDTO address;
}
