package jrs.mystorage.item.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Currency;
import java.util.UUID;

@Data
@Entity
@Immutable
@Table(name = "storage_item_view")
@Subselect("select * from storage_item_view")
public class StorageItemView {

    @Id
    private UUID itemId;
    private Integer amount;
    private Double value;
    private Double totalValue;
    private Currency currency;

    private UUID productId;
    private String productName;
    private String productDescription;

    private UUID storageId;
}
