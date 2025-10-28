package com.ecom.app.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String country;
    private String street;
    private String city;
    private String state;
    private String zipcode;


}
