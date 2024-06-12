package com.otters.computerstore.component.saleProduct;

import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.component.product.ProductRepo;
import com.otters.computerstore.component.product.ProductService;
import com.otters.computerstore.component.product.dto.ProductDTO;
import com.otters.computerstore.component.saleBill.SaleBillRepo;
import com.otters.computerstore.component.saleProduct.dto.SaleProductDTO;
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
public class SaleProductService {
    private final ProductService productService;
    private final SaleProductRepo repo;
    private final ProductRepo productRepo;
    private final SaleBillRepo saleRepo;
    private final DTOtoEntityMapper mapper;
    public SaleProductEntity getSaleProduct(String saleProductId) {
        Optional<SaleProductEntity> saleProduct = repo.findById(saleProductId);
        if (saleProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return saleProduct.get();
    }
    public SaleProductEntity getSaleBill(String saleBillId) {
        Optional<SaleProductEntity> saleProduct = repo.findById(saleBillId);
        if (saleProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return saleProduct.get();
    }

    public SaleProductDTO random() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "123456789";
        ProductDTO productDTO = productService.random();
        ProductEntity productEntity = productService.add(productDTO);
        String productId = productEntity.getId();
        Long price = Long.valueOf(Random.random(Integer.valueOf(Random.random(1, "1234567")), numbers))*1000l;
        Integer quantity = Integer.valueOf(Random.random(Integer.valueOf(Random.random(1, "123")), numbers));
        String seri = Random.random(10, numbers);
        return new SaleProductDTO(productId, quantity, price, seri);
    }
}