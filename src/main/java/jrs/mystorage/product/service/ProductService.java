package jrs.mystorage.product.service;

import jrs.mystorage.product.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> getProducts(String userEmail, UUID ownerId);
}
