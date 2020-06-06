package jrs.mystorage.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CUStorageDto {

    @Length(min = 3, max = 255)
    private String name;

    @Min(1)
    private Double surface;

    @Length(min = 3)
    private String addressStreet;

    @Length(min = 3)
    private String addressCity;

    @Length(min = 3)
    private String addressZip;

    @Length(min = 3)
    private String addressCountry;
}
