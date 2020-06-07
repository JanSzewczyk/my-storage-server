package jrs.mystorage.product.service.impl;

import jrs.mystorage.product.dto.ProductDto;
import jrs.mystorage.product.repository.ProductRepository;
import jrs.mystorage.product.service.ProductService;
import jrs.mystorage.utils.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getProducts(String userEmail, UUID ownerId) {
        return productRepository.findAllByOwnerOwnerId(ownerId)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
