package jrs.mystorage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false)
    private UUID productId;

    @Column
    @Min(1)
    private Integer amount;

    @Column
    @Min(0)
    private Double valuePerItem;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
            CascadeType.REFRESH}
            )
    @JoinColumn(name = "product_id")
    private Product product;
}
