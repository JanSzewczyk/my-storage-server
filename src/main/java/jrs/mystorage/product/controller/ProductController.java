package jrs.mystorage.product.controller;

import jrs.mystorage.item.dto.StorageItemDto;
import jrs.mystorage.product.dto.ProductDto;
import jrs.mystorage.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{ownerId}")
    @PreAuthorize(value = "hasAuthority('EMPLOYEE')")
    public ResponseEntity<List<ProductDto>> getAllProducts(
            final Principal principal,
            @PathVariable UUID ownerId
    ) {
        List<ProductDto> products = productService.getProducts(principal.getName(), ownerId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
