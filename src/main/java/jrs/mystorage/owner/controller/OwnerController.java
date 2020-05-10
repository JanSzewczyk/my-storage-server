package jrs.mystorage.owner.controller;

import jrs.mystorage.owner.dto.OwnerDto;
import jrs.mystorage.owner.dto.UOwnerDto;
import jrs.mystorage.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PutMapping
    @PreAuthorize(value = "hasAuthority('OWNER')")
    public ResponseEntity<OwnerDto> updateEmployee(
            final Principal principal,
            @RequestBody @Valid UOwnerDto updatedOwner
    ) {
        OwnerDto owner = ownerService.updateOwner(updatedOwner, principal.getName());
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

}
