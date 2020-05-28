package jrs.mystorage.action.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveActionItemDto {

    @NotNull
    private UUID productId;

    @Min(1)
    private Integer amount;
}
