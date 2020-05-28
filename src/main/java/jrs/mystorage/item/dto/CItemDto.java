package jrs.mystorage.item.dto;

import jrs.mystorage.product.dto.CProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CItemDto {

    @Min(1)
    private Integer amount;

    private UUID productId;

    private CProductDto newProduct;
}
