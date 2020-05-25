package jrs.mystorage.action.dto;

import jrs.mystorage.action.model.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionDto {
    private UUID actionId;

    private ActionType action;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
