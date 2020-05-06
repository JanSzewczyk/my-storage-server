package jrs.mystorage.owner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {

    private UUID ownerId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
