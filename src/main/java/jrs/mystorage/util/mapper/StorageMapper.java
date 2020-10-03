package jrs.mystorage.util.mapper;

import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.model.Storage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class StorageMapper extends Mapper<Storage, StorageDto> {

    private final ModelMapper mapper;

    @PostConstruct
    public void init() {
    }

    @Override
    public Storage toEntity(StorageDto storageDto) {
        return mapper.map(storageDto, Storage.class);
    }

    public Storage toEntity(CUStorageDto storageDto) {
        return mapper.map(storageDto, Storage.class);
    }

    public Storage updateEntity(CUStorageDto storageDto, Storage storage) {
        mapper.map(storageDto, storage);
        return storage;
    }

    @Override
    public StorageDto toDto(Storage storage) {
        return mapper.map(storage, StorageDto.class);
    }

}
