package com.ecom.app.service;

import com.ecom.app.dto.ProductReqDTO;
import com.ecom.app.dto.ProductRespDTO;
import com.ecom.app.entity.Product;
import com.ecom.app.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductRespDTO createProduct(ProductReqDTO reqDTO) {
        Product  product = new Product();
        updateProductFromReq(product, reqDTO);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    private ProductRespDTO mapToProductResponse(Product savedProduct) {
        ProductRespDTO productRespDTO = new ProductRespDTO();
        productRespDTO.setId(savedProduct.getId());
        productRespDTO.setName(savedProduct.getName());
        productRespDTO.setPrice(savedProduct.getPrice());
        productRespDTO.setCategory(savedProduct.getCategory());
        productRespDTO.setDescription(savedProduct.getDescription());
        productRespDTO.setImageUrl(savedProduct.getImageUrl());
        productRespDTO.setActive(savedProduct.getActive());
        productRespDTO.setStockQuantity(savedProduct.getStockQuantity());
        return productRespDTO;

    }

    private void updateProductFromReq(Product product, ProductReqDTO reqDTO) {
        product.setName(reqDTO.getName());
        product.setPrice(reqDTO.getPrice());
        product.setCategory(reqDTO.getCategory());
        product.setDescription(reqDTO.getDescription());
        product.setImageUrl(reqDTO.getImageUrl());
        product.setStockQuantity(reqDTO.getStockQuantity());
    }

    public ProductRespDTO updateProduct(Long id, ProductReqDTO reqDTO) {
        productRepository.findById(id)
                .map(existingProduct -> {
                    updateProductFromReq(existingProduct, reqDTO);
                    Product saveProduct =  productRepository.save(existingProduct);
                    return mapToProductResponse(saveProduct);
                }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
