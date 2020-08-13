package jrs.mystorage.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageViewDto {

    private UUID id;
    private String name;
    private Double surface;
    private String addressStreet;
    private String addressCity;
    private String addressZip;
    private String addressCountry;
    private int numberOfEmployees = 0;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
