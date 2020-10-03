package jrs.mystorage.owner.service;

import jrs.mystorage.owner.dto.OwnerDto;
import jrs.mystorage.owner.dto.UOwnerDto;
import jrs.mystorage.owner.model.Owner;

public interface OwnerService {

    OwnerDto updateOwner(UOwnerDto updatedOwner, String ownerEmail);

    Owner findOwnerByEmail(String ownerEmail);
}
