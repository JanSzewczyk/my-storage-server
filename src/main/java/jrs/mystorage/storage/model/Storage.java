package jrs.mystorage.storage.model;

import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.entity.Item;
import jrs.mystorage.owner.model.Owner;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false)
    private UUID storageId;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
                    )
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(
            mappedBy = "storage",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
                    )
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(
            mappedBy = "storage",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Item> items = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
}
