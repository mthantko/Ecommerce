package com.ecom.app.dto;

import lombok.Data;

@Data
public class ProductRespDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String price;
    private String imageUrl;
    private String stockQuantity;
    private Boolean active;
}
