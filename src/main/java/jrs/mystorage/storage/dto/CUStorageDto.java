package jrs.mystorage.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CUStorageDto {

    @NotBlank
    @Length(min = 3, max = 255)
    private String name;

    @Min(1)
    private Double surface;

    @NotBlank
    @Length(min = 3)
    private String addressStreet;

    @NotBlank
    @Length(min = 3)
    private String addressCity;

    @NotBlank
    @Length(min = 3)
    private String addressZip;

    @NotBlank
    @Length(min = 3)
    private String addressCountry;
}
