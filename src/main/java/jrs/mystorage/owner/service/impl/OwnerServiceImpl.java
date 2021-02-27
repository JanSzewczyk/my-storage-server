package jrs.mystorage.owner.service.impl;

import jrs.mystorage.owner.dto.OwnerDto;
import jrs.mystorage.owner.dto.UOwnerDto;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.owner.service.OwnerService;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.OwnerMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import static jrs.mystorage.util.MessageTemplates.OWNER_NOT_FOUND_MESSAGE_TEMPLATE;


@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public OwnerDto updateOwner(UOwnerDto updatedOwner, String ownerEmail) {
        Owner owner = ownerRepository.findByEmail(ownerEmail)
                .orElseThrow(NotFoundException::new);

        if (updatedOwner.getPassword().equals("")) {
            updatedOwner.setPassword(owner.getPassword());
        }

        ownerMapper.updateEntity(updatedOwner, owner);
        ownerRepository.save(owner);

        return ownerMapper.toDto(owner);
    }

    @Override
    public Owner findOwnerByEmail(String ownerEmail) {
        return ownerRepository
                .findByEmail(ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(OWNER_NOT_FOUND_MESSAGE_TEMPLATE, ownerEmail)));
    }
}
