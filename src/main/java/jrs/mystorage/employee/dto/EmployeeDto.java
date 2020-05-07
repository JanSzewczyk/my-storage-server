package jrs.mystorage.employee.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jrs.mystorage.utils.validator.annotation.PasswordValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private UUID employeeId;

    private String email;

    @PasswordValid
    private String password;

    @NotNull
    private String firstName;

    private String lastName;

    @Size(max = 16)
    private String phone;

    @Size(min = 3)
    private String addressStreet;

    @Size(min = 3)
    private String addressCity;

    @Size(min = 3)
    private String addressZip;

    @Size(min = 3)
    private String addressState;

    @Size(min = 3)
    private String addressCountry;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
