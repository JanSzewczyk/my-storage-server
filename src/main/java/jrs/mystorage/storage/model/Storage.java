package jrs.mystorage.storage.model;

import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.entity.Item;
import jrs.mystorage.owner.model.Owner;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(insertable = false, updatable = false)
    private UUID storageId;

    @Column(nullable = false)
    @Length(min = 3, max = 255)
    private String name;

    @Column(nullable = false)
    @Min(0)
    private Double surface;

    @Column
    @Length(min = 3)
    private String addressStreet;

    @Column
    @Length(min = 3)
    private String addressCity;

    @Column
    @Length(min = 3)
    private String addressZip;

    @Column
    private String addressCountry;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(
            mappedBy = "storage",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(
            mappedBy = "storage",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Item> items = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;

    public Integer getNumberOfEmployees() {
        return employees.size();
    }
}
