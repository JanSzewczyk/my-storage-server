package jrs.mystorage.employee.dto;

import jrs.mystorage.utils.validator.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UEmployeeDto {

    @EmailValid
    private String email;

    @PasswordOrEmptyValid
    private String password;

    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

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
}
