package com.otters.computerstore.component.warrantyProduct;

import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.component.product.ProductRepo;
import com.otters.computerstore.component.product.ProductService;
import com.otters.computerstore.component.product.dto.ProductDTO;
import com.otters.computerstore.component.warrantyBill.WarrantyBillRepo;
import com.otters.computerstore.component.warrantyProduct.dto.WarrantyProductDTO;
import com.otters.computerstore.mapper.DTOtoEntityMapper;
import com.otters.computerstore.utils.Random;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class WarrantyProductService {
    private final ProductService productService;
    private final WarrantyProductRepo repo;
    private final ProductRepo productRepo;
    private final WarrantyBillRepo warrantyRepo;
    private final DTOtoEntityMapper mapper;
    public WarrantyProductEntity getWarrantyProduct(String warrantyProductId) {
        Optional<WarrantyProductEntity> warrantyProduct = repo.findById(warrantyProductId);
        if (warrantyProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return warrantyProduct.get();
    }
    public WarrantyProductEntity getWarrantyBill(String warrantyBillId) {
        Optional<WarrantyProductEntity> warrantyProduct = repo.findById(warrantyBillId);
        if (warrantyProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return warrantyProduct.get();
    }

    public WarrantyProductDTO random() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "123456789";
        ProductDTO productDTO = productService.random();
        ProductEntity productEntity = productService.add(productDTO);
        String productId = productEntity.getId();
        String warrantyContent = Random.random(20, characters);
        String status = Random.random(20, characters);
        String note = Random.random(20, characters);
        Integer quantity = Integer.valueOf(Random.random(Integer.valueOf(Random.random(1, "12")), numbers));
        return new WarrantyProductDTO(productId, quantity, warrantyContent, status, note);
    }
}