package jrs.mystorage.employee.dto;

import jrs.mystorage.storage.dto.StorageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "employees", itemRelation = "employee")
public class EmployeeViewDto extends RepresentationModel<EmployeeViewDto> {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String addressStreet;
    private String addressCity;
    private String addressZip;
    private String addressCountry;

    private UUID storageId;
    private String storageName;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
