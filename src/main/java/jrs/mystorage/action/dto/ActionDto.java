package jrs.mystorage.action.dto;

import jrs.mystorage.action.model.ActionType;
import jrs.mystorage.item.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "actions", itemRelation = "action")
public class ActionDto extends RepresentationModel<ActionDto> {

    private UUID Id;
    private ActionType action;
    private Timestamp createdAt;

    private UUID employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    private UUID storageId;
    private String storageShortId;
    private String storageName;

    private Currency currency;

    private List<ItemDto> items = new ArrayList<>();
}
