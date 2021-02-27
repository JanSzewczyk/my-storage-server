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
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.model.StorageView;
import jrs.mystorage.storage.repository.StorageRepository;
import jrs.mystorage.storage.repository.StorageViewRepository;
import jrs.mystorage.storage.service.StorageService;
import jrs.mystorage.user.service.UserService;
import jrs.mystorage.util.ShortID;
import jrs.mystorage.util.exception.ConflictException;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.StorageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static jrs.mystorage.util.MessageTemplates.OWNER_NOT_FOUND_MESSAGE_TEMPLATE;
import static jrs.mystorage.util.MessageTemplates.STORAGE_NOT_FOUND_MESSAGE_TEMPLATE;
import static jrs.mystorage.util.database.SpecificationBuildHelper.containsTextInAttributes;
import static jrs.mystorage.util.database.SpecificationBuildHelper.fieldEquals;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private static final List<String> STORAGE_SEARCH_FIELDS =
            List.of("id", "shortId", "name", "addressCity", "addressStreet", "addressCountry");

    private final UserService userService;
    private final StorageRepository storageRepository;
    private final StorageViewRepository storageViewRepository;
    private final StorageMapper storageMapper;
    private final OwnerRepository ownerRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<Storage> findAllStorages(UUID ownerId) {
        return storageRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public StorageDto getStorage(String userEmail, UUID storageId) {
        Role role = userService.getUserTypeByEmail(userEmail);
        Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));

        if ((role == Role.OWNER && storage.getOwner().getEmail().equals(userEmail)) || (role == Role.EMPLOYEE && storage.getEmployees().stream().anyMatch(employee -> employee.getEmail().equals(userEmail)))) {
            return storageMapper.toDto(storage);
        } else {
            throw new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId));
        }
    }

    @Override
    public StorageDto createStorage(String ownerEmail, CUStorageDto newStorage) {
        Owner owner = ownerRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(OWNER_NOT_FOUND_MESSAGE_TEMPLATE, ownerEmail)));

        Storage storage = storageMapper.toEntity(newStorage);
        storage.setOwner(owner);
        storage.setShortId(this.generateShortId());

        return storageMapper.toDto(storageRepository.save(storage));
    }

    @Override
    public StorageDto updateStorage(String ownerEmail, UUID storageId, CUStorageDto updatedStorage) {
        Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));

        storage = storageMapper.updateEntity(updatedStorage, storage);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    @Override
    public StorageDto removeOwnerStorage(String ownerEmail, UUID storageId) {
        Storage storage = storageRepository.findByIdAndOwnerEmail(storageId, ownerEmail)
                .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));

        storageRepository.delete(storage);
        return storageMapper.toDto(storage);
    }

    @Override
    public void storeItemsInStorage(UUID storageId, List<Item> items) {
        Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));

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
                .orElseThrow(() -> new NotFoundException(String.format(STORAGE_NOT_FOUND_MESSAGE_TEMPLATE, storageId)));

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

    @Override
    public Page<StorageView> findAllStoragesByOwnerId(UUID ownerId, Pageable pageable, String search) {
        Specification<StorageView> ownerIdSpec = fieldEquals("ownerId", ownerId);
        Specification<StorageView> storageViewSpec = getStorageViewSpecification(search);
        return storageViewRepository.findAll(ownerIdSpec.and(storageViewSpec) , pageable);
    }

    private Specification<StorageView> getStorageViewSpecification(String searchString) {
//        Specification<OfferView> containsStateSpecification = in(OFFER_VIEW_STATE, states);
        Specification<StorageView> containsTextInAttributesSpecification =
                containsTextInAttributes(searchString, STORAGE_SEARCH_FIELDS);

//        return states != null ? containsStateSpecification.and(containsTextInAttributesSpecification) : containsTextInAttributesSpecification;
        return containsTextInAttributesSpecification;
    }

    private String generateShortId() {
        String generatedID;
        do {
            generatedID = ShortID.randomShortID();
        } while (storageRepository.existsByShortId(generatedID));

        return generatedID;
    }
}
