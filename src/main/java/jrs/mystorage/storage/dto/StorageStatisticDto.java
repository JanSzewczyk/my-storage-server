package jrs.mystorage.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageStatisticDto {

    private LocalDate date;
    private Currency currency;

    private Double totalValue = 0.0;
    private Double storedValue = 0.0;
    private Double removeValue = 0.0;
}
