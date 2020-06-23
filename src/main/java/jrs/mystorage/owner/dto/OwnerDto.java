package jrs.mystorage.owner.dto;

import jrs.mystorage.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;
import java.util.Currency;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "employees", itemRelation = "employee")
public class OwnerDto extends RepresentationModel<OwnerDto> implements UserDto {

    private UUID ownerId;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Currency currency;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
