package jrs.mystorage.entity;

import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.storage.model.Storage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false)
    private UUID productId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "storage_id")
    private Storage storage;
}
