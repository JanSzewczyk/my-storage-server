package jrs.mystorage.owner.dto;

import jrs.mystorage.utils.validator.annotation.EmailValid;
import jrs.mystorage.utils.validator.annotation.PasswordOrEmptyValid;
import jrs.mystorage.utils.validator.annotation.PasswordValid;
import jrs.mystorage.utils.validator.annotation.PhoneNoValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UOwnerDto {

    @PasswordOrEmptyValid
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @PhoneNoValid
    private String phone;
}
