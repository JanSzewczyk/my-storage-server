package jrs.mystorage.utils.mapper;

import jrs.mystorage.entity.CProductDto;
import jrs.mystorage.entity.Product;
import jrs.mystorage.entity.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ProductMapper extends Mapper<Product, ProductDto> {

    private final ModelMapper mapper;

    @PostConstruct
    public void init() {
//        mapper.typeMap(UEmployeeDto.class, Employee.class).addMappings(m -> {
//            m.skip(Employee::setEmployeeId);
//        });
//
//        mapper.typeMap(CEmployeeDto.class, Employee.class).addMappings(m -> {
//            m.skip(Employee::setEmployeeId);
//        });
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }

    public Product toEntity(CProductDto newProduct) {
        return mapper.map(newProduct, Product.class);
    }

    @Override
    public ProductDto toDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }
}
