package com.ecom.app.controller;

import com.ecom.app.dto.ProductReqDTO;
import com.ecom.app.dto.ProductRespDTO;
import com.ecom.app.repository.ProductRepository;
import com.ecom.app.service.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductRespDTO> createProduct(@RequestBody ProductReqDTO reqDTO) {
        return new ResponseEntity<>(productService.createProduct(reqDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRespDTO> updateProduct(@PathVariable Long id,@RequestBody ProductReqDTO reqDTO) {
        return new ResponseEntity<>(productService.updateProduct(id, reqDTO),
                HttpStatus.OK);
    }
}
