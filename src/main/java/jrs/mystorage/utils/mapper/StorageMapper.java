package jrs.mystorage.utils.mapper;

import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.model.Storage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageMapper extends Mapper<Storage, StorageDto> {

    private final ModelMapper mapper;

    @Override
    public Storage toEntity(StorageDto storageDto) {
        return mapper.map(storageDto, Storage.class);
    }

    @Override
    public StorageDto toDto(Storage storage) {
        return mapper.map(storage, StorageDto.class);
    }
}
