package jrs.mystorage.utils.mapper;

import jrs.mystorage.owner.dto.OwnerDto;
import jrs.mystorage.owner.model.Owner;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerMapper extends Mapper<Owner, OwnerDto> {

    private final ModelMapper mapper;

    @Override
    public Owner toEntity(OwnerDto ownerDto) {
        return mapper.map(ownerDto, Owner.class);
    }

    @Override
    public OwnerDto toDto(Owner owner) {
        return mapper.map(owner, OwnerDto.class);
    }
}
