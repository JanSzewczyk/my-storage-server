package jrs.mystorage.action.dto;

import jrs.mystorage.action.model.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "actions", itemRelation = "action")
public class ActionStorageDto extends RepresentationModel<ActionStorageDto> {

    private UUID actionId;
    private ActionType action;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private UUID itemId;
    private String itemName;
    private Integer itemAmount;
    private Double itemValue;
    private Double itemTotalValue;

    private UUID employeeId;
    private String employeeFirstName;
    private String employeeLastName;
}
