package jrs.mystorage.owner.model;

import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.product.model.Product;
import jrs.mystorage.storage.model.Storage;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(insertable = false, updatable = false)
    private UUID id;

    @Column(updatable = false, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Length(max = 16)
    @Column(length = 32, unique = true)
    private String phone;

    @OneToMany(
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Storage> storages = new ArrayList<>();

    @OneToMany(
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Product> products = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
}
