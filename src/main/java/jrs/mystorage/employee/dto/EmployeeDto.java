package jrs.mystorage.employee.dto;

import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements UserDto {

    private UUID employeeId;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String addressStreet;

    private String addressCity;

    private String addressZip;

    private String addressCountry;

    private UUID ownerId;

    private StorageDto workPlace;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
