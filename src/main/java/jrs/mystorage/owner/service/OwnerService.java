package jrs.mystorage.owner.service;

import jrs.mystorage.owner.dto.OwnerDto;
import jrs.mystorage.owner.dto.UOwnerDto;

public interface OwnerService {

    OwnerDto updateOwner(UOwnerDto updatedOwner, String ownerEmail);
}
