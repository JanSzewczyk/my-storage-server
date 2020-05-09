package jrs.mystorage.employee.model;

import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.storage.model.Storage;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @Column(insertable = false, updatable = false)
    private UUID employeeId;

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

    @Column
    @Length(min = 3)
    private String addressStreet;

    @Column
    @Length(min = 3)
    private String addressCity;

    @Column
    @Length(min = 3)
    private String addressZip;

    @Column()
    private String addressCountry;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
}
