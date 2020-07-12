package jrs.mystorage.storage.service.impl;

import jrs.mystorage.action.dto.RemoveActionItemDto;
import jrs.mystorage.action.model.Action;
import jrs.mystorage.action.model.ActionType;
import jrs.mystorage.auth.model.Role;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.item.repository.ItemRepository;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.owner.repository.OwnerRepository;
import jrs.mystorage.storage.dto.CUStorageDto;
import jrs.mystorage.storage.dto.StorageDto;
import jrs.mystorage.storage.dto.StorageStatisticDto;
import jrs.mystorage.storage.dto.StorageViewDto;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.repository.StorageRepository;
import jrs.mystorage.storage.service.StorageService;
import jrs.mystorage.user.service.UserService;
import jrs.mystorage.util.exception.ConflictException;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.StorageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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
        Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                .orElseThrow(NotFoundException::new);

        storage = storageMapper.updateEntity(updatedStorage, storage);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    @Override
    public StorageDto removeOwnerStorage(String ownerEmail, UUID storageId) {
        Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
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
                    .findByStorageIdAndProductId(storageId, item.getProduct().getId());
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

    @Override
    public void removeStorageItems(UUID storageId, ArrayList<RemoveActionItemDto> removedItems) {
        List<Item> updatedItems = new ArrayList<>();

        removedItems.forEach(item -> {
            Item updatingItem = itemRepository.findByStorageIdAndProductId(storageId, item.getProductId())
                    .orElseThrow(NotFoundException::new);
            if (updatingItem.getAmount() > item.getAmount()){
                updatingItem.setAmount(updatingItem.getAmount() - item.getAmount());
                updatedItems.add(updatingItem);
            } else if (updatingItem.getAmount().equals(item.getAmount())) {
                itemRepository.delete(updatingItem);
            } else {
                throw new ConflictException();
            }
        });

        itemRepository.saveAll(updatedItems);
    }

    @Override
    public List<StorageStatisticDto> getStorageValueStatistics(String ownerEmail, UUID storageId) {
        List<StorageStatisticDto> statistics = new ArrayList<>();

        Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                .orElseThrow(NotFoundException::new);

        Currency currency = storage.getOwner().getCurrency();
        List<Action> actions = storage.getActions();

        LocalDate startDate = storage.getCreatedAt().toLocalDateTime().toLocalDate();
        LocalDate endDate = LocalDate.now().plusDays(1);
        double totalValue = 0.0;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            LocalDate finalDate = date;
            double storeValue = 0.0;
            double removeValue = 0.0;

            StorageStatisticDto values = new StorageStatisticDto();
            values.setDate(finalDate);
            values.setCurrency(currency);

            List<Action> dailyActions = actions
                    .stream()
                    .filter(action -> action.getCreatedAt().toLocalDateTime().toLocalDate().equals(finalDate))
                    .collect(Collectors.toList());

            for (Action da : dailyActions) {
                double sum = da.getItems()
                        .stream()
                        .map(i -> i.getAmount() * i.getProduct().getValue())
                        .mapToDouble(Double::doubleValue)
                        .sum();

                if (da.getAction() == ActionType.STORE) {
                    storeValue += sum;
                }
                if(da.getAction() == ActionType.REMOVE) {
                    removeValue += sum;
                }
            }

            totalValue += storeValue;
            totalValue -= removeValue;

            values.setStoredValue(storeValue);
            values.setRemoveValue(removeValue);
            values.setTotalValue(totalValue);
            statistics.add(values);
        }

        return statistics;
    }
}
