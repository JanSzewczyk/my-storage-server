package jrs.mystorage.storage.model;

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
@Table(name = "storage_view")
@Subselect("select * from storage_view")
public class StorageView {

    @Id
    private UUID id;
    private String shortId;
    private String name;
    private Double surface;
    private String addressStreet;
    private String addressCity;
    private String addressZip;
    private String addressCountry;
    private Integer numberOfEmployees;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private UUID ownerId;

    private Timestamp lastActionDate;
}
