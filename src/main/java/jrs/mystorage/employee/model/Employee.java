package jrs.mystorage.employee.model;

import jrs.mystorage.action.model.Action;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.storage.model.Storage;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, unique = true)
    private UUID id;

    @Column(nullable = false, updatable = false, unique = true)
    private String shortId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Length(max = 16)
    @Column(length = 32)
    private String phone;

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

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;

    //TODO Change cascade Types
    @OneToMany(
            mappedBy = "employee",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Action> actions = new ArrayList<>();

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

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "storage_id")
    private Storage storage;
}
