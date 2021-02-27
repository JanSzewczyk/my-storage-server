package jrs.mystorage.action.controller;

import jrs.mystorage.action.ActionComponent;
import jrs.mystorage.action.dto.ActionDto;
import jrs.mystorage.action.dto.RemoveActionItemDto;
import jrs.mystorage.action.service.ActionService;
import jrs.mystorage.item.dto.CItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class ActionController {

    private final ActionService actionService;
    private final ActionComponent actionComponent;

    @GetMapping("storage/{storageId}/actions")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<ActionDto>> getStorageActions(
            final Principal principal,
            Pageable pageable,
            @PathVariable UUID storageId
    ) {
        PagedModel<ActionDto> allStorageActions = actionService.getAllStorageActions(principal.getName(), storageId, pageable);
        return new ResponseEntity<>(allStorageActions, HttpStatus.OK);
    }

    @GetMapping("employee/{employeeId}/actions")
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<PagedModel<ActionDto>> getEmployeeActions(
            final Principal principal,
            Pageable pageable,
            @PathVariable UUID employeeId
    ) {
        PagedModel<ActionDto> allEmployeeActions = actionComponent.findAllEmployeeActions(employeeId, principal.getName(), pageable);
        return new ResponseEntity<>(allEmployeeActions, HttpStatus.OK);
    }

    // TODO return action dto
    @PostMapping("actions/store")
    @PreAuthorize(value = "hasAuthority('EMPLOYEE')")
    public ResponseEntity<Void> storeItemsAction(
            final Principal principal,
            @RequestBody ArrayList<CItemDto> newItems
    ) {
        actionService.storeItemsInStorage(principal.getName(), newItems);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // TODO return action dto
    @PostMapping("actions/remove")
    @PreAuthorize(value = "hasAuthority('EMPLOYEE')")
    public ResponseEntity<Void> removeItemsAction(
            final Principal principal,
            @RequestBody @Valid ArrayList<RemoveActionItemDto> removedItems
    ) {
        actionService.removeItemsFromStorage(principal.getName(), removedItems);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
