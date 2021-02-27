package jrs.mystorage.util.mapper;

import jrs.mystorage.MyStorageApplication;
import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.product.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MyStorageApplication.class)
class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    void shouldConvertItemToItemDto() {
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setAmount(123);

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setDescription("Product description.");
        product.setName("Product");
        product.setValue(49.99);

        item.setProduct(product);

        ItemDto itemDto = itemMapper.toDto(item);

        assertAll(
                () -> assertThat(itemDto.getId()).isEqualTo(item.getId()),
                () -> assertThat(itemDto.getAmount()).isEqualTo(item.getAmount()),
                () -> assertThat(itemDto.getProductId()).isEqualTo(product.getId()),
                () -> assertThat(itemDto.getProductName()).isEqualTo(product.getName()),
                () -> assertThat(itemDto.getProductValue()).isEqualTo(product.getValue()),
                () -> assertThat(itemDto.getTotalValue()).isEqualTo(product.getValue() * item.getAmount())
        );
    }
}