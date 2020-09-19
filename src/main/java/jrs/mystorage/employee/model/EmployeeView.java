package jrs.mystorage.employee.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Immutable
@Table(name = "employee_view")
@Subselect("select * from employee_view")
public class EmployeeView {

    @Id
    private UUID id;
    private String shortId;
    private String email;
    private String name;
    private String phone;
    private String addressStreet;
    private String addressCity;
    private String addressZip;
    private String addressCountry;
    private Timestamp updatedAt;
    private Timestamp createdAt;

    private UUID storageId;
    private String storageName;

    private UUID ownerId;
    private String ownerEmail;
}
