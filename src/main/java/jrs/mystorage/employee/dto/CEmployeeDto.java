package jrs.mystorage.employee.dto;

import jrs.mystorage.util.validator.annotation.EmailValid;
import jrs.mystorage.util.validator.annotation.PasswordValid;
import jrs.mystorage.util.validator.annotation.PhoneNoValid;
import jrs.mystorage.util.validator.annotation.UniqueEmailValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CEmployeeDto {

    @EmailValid
    @UniqueEmailValid
    private String email;

    @PasswordValid
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

    private UUID storageId;
}
