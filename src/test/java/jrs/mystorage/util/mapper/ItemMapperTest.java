package jrs.mystorage.util.mapper;

import jrs.mystorage.MyStorageApplication;
import jrs.mystorage.item.dto.ItemDto;
import jrs.mystorage.item.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MyStorageApplication.class)
class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    void checkMapToDto() {
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setAmount(123);

        ItemDto itemDto = itemMapper.toDto(item);

        assertEquals(itemDto.getId(), item.getId());
        assertEquals(itemDto.getAmount(), item.getAmount());
    }
}