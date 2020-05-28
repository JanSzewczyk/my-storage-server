package jrs.mystorage.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CProductDto {

    private String name;

    private String description;

    @Min(0)
    private Double value;
}
