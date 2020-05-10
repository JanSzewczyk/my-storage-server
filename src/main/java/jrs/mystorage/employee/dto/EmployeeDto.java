package jrs.mystorage.employee.dto;

import jrs.mystorage.user.dto.UserDto;
import jrs.mystorage.utils.validator.annotation.EmailValid;
import jrs.mystorage.utils.validator.annotation.PhoneNoValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "employees", itemRelation = "employee")
public class EmployeeDto extends RepresentationModel<EmployeeDto> implements UserDto {

    private UUID employeeId;

    @EmailValid
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Size(max = 16)
    @PhoneNoValid
    private String phone;

    @Size(min = 3)
    private String addressStreet;

    @Size(min = 3)
    private String addressCity;

    @Size(min = 3)
    private String addressZip;

    @Size(min = 3)
    private String addressCountry;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
