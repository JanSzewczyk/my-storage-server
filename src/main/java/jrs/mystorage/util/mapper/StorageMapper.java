package jrs.mystorage.util.mapper;

import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.dto.StorageViewDto;
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
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
//        mapper
//                .cre(Storage.class, StorageViewDto.class)
//                .addMappings(m -> {
//                    m.map(s -> s.getEmployees().size(), StorageViewDto::setNumberOfEmployees);
//                });
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

    public StorageViewDto toViewDto(Storage storage) {
        StorageViewDto map = mapper.map(storage, StorageViewDto.class);
        map.setNumberOfEmployees(storage.getNumberOfEmployees());
        return map;
    }
}
