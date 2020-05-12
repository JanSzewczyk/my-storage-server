package jrs.mystorage.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "storages", itemRelation = "storage")
public class StorageDto extends RepresentationModel<StorageDto> {

    private UUID storageId;
    private String name;
    private Double surface;
    private String addressStreet;
    private String addressCity;
    private String addressZip;
    private String addressCountry;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
