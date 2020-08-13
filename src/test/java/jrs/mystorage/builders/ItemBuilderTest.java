package jrs.mystorage.builders;

import jrs.mystorage.action.model.Action;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.product.model.Product;
import jrs.mystorage.storage.model.Storage;
import lombok.Builder;

import java.util.UUID;

public class ItemBuilderTest {

    @Builder
    static Item getItem (UUID id, Integer amount, Product product, Storage storage, Action action) {
        return new Item(id, amount, product, storage, action);
    }
}
