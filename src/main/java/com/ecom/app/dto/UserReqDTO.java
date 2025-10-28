package com.ecom.app.dto;

import lombok.Data;

@Data
public class UserReqDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private  String email;
    private String phoneNumber;
    private AddressDTO address;
}
