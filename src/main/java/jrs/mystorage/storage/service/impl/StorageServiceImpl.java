package jrs.mystorage.storage.service.impl;

import jrs.mystorage.auth.model.Role;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.item.repository.ItemRepository;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.dto.StorageViewDto;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.repository.StorageRepository;
import jrs.mystorage.storage.service.StorageService;
import jrs.mystorage.user.service.UserService;
import jrs.mystorage.utils.exception.NotFoundException;
import jrs.mystorage.utils.mapper.StorageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final UserService userService;
    private final StorageRepository storageRepository;
    private final StorageMapper storageMapper;
    private final OwnerRepository ownerRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<StorageViewDto> getOwnerStorages(String ownerEmail) {
        List<Storage> storages = storageRepository.findAllByOwnerEmail(ownerEmail);

        return storages
                .stream()
                .map(storageMapper::toViewDto)
                .collect(Collectors.toList());
    }

    @Override
    public StorageDto getStorage(String userEmail, UUID storageId) {
        Role role = userService.getUserTypeByEmail(userEmail);
        Storage storage = storageRepository.findById(storageId)
                .orElseThrow(NotFoundException::new);

        if ((role == Role.OWNER && storage.getOwner().getEmail().equals(userEmail)) || (role == Role.EMPLOYEE && storage.getEmployees().stream().anyMatch(employee -> employee.getEmail().equals(userEmail)))) {
            return storageMapper.toDto(storage);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public StorageDto createStorage(String ownerEmail, CUStorageDto newStorage) {
        Owner owner = ownerRepository.findByEmail(ownerEmail)
                .orElseThrow(NotFoundException::new);

        Storage storage = storageMapper.toEntity(newStorage);
        storage.setOwner(owner);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    @Override
    public StorageDto updateStorage(String ownerEmail, UUID storageId, CUStorageDto updatedStorage) {
        Storage storage = storageRepository.findByStorageIdAndOwnerEmail(storageId, ownerEmail)
                .orElseThrow(NotFoundException::new);

        storage = storageMapper.updateEntity(updatedStorage, storage);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    @Override
    public StorageDto removeOwnerStorage(String ownerEmail, UUID storageId) {
        Storage storage = storageRepository.findByStorageIdAndOwnerEmail(storageId, ownerEmail)
                .orElseThrow(NotFoundException::new);

        storageRepository.delete(storage);
        return storageMapper.toDto(storage);
    }

    @Override
    public void storeItemsInStorage(UUID storageId, List<Item> items) {
        Storage storage = storageRepository.findById(storageId)
                .orElseThrow(NotFoundException::new);

        items.forEach(item -> {
            Optional<Item> findItem = itemRepository
                    .findByStorageStorageIdAndProductProductId(storageId, item.getProduct().getProductId());
            if (findItem.isPresent()){
                Item updatingItem = findItem.get();
                updatingItem.setAmount(updatingItem.getAmount() + item.getAmount());
                itemRepository.save(updatingItem);
            } else {
                Item newItem = new Item();
                newItem.setAmount(item.getAmount());
                newItem.setProduct(item.getProduct());
                newItem.setStorage(storage);
                itemRepository.save(newItem);
            }
        });

    }


}
