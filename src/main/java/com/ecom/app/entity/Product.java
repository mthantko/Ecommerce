package com.ecom.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Data
@Entity(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String category;
    private String price;
    private String imageUrl;
    private String stockQuantity;
    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime  createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
