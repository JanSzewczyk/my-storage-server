package jrs.mystorage.owner.dto;

import jrs.mystorage.util.validator.annotation.PasswordOrEmptyValid;
import jrs.mystorage.util.validator.annotation.PhoneNoValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Currency;

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

    @NotNull
    private Currency currency;
}
