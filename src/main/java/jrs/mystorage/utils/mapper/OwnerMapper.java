package jrs.mystorage.utils.mapper;

import jrs.mystorage.owner.dto.OwnerDto;
import jrs.mystorage.owner.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper extends Mapper<Owner, OwnerDto> {

    @Override
    public Owner toEntity(OwnerDto ownerDto) {
        return null;
    }

    @Override
    public OwnerDto toDto(Owner owner) {
        return null;
    }
}
