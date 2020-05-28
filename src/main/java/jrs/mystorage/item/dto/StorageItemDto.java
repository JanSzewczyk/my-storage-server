package jrs.mystorage.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "items", itemRelation = "item")
public class StorageItemDto extends RepresentationModel<StorageItemDto> {

    private UUID itemId;
    private Integer amount;

    private UUID productId;
    private String productName;
    private Double productValue;

    private Double totalValue;
}
