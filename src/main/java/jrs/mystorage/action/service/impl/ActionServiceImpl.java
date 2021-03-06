package jrs.mystorage.action.service.impl;

import jrs.mystorage.action.dto.ActionDto;
import jrs.mystorage.action.dto.RemoveActionItemDto;
import jrs.mystorage.action.model.Action;
import jrs.mystorage.action.model.ActionType;
import jrs.mystorage.action.repository.ActionRepository;
import jrs.mystorage.action.service.ActionService;
import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.employee.repository.EmployeeRepository;
import jrs.mystorage.product.model.Product;
import jrs.mystorage.product.repository.ProductRepository;
import jrs.mystorage.item.dto.CItemDto;
import jrs.mystorage.item.model.Item;
import jrs.mystorage.item.repository.ItemRepository;
import jrs.mystorage.owner.model.Owner;
import jrs.mystorage.storage.model.Storage;
import jrs.mystorage.storage.service.StorageService;
import jrs.mystorage.util.exception.NotFoundException;
import jrs.mystorage.util.mapper.ActionMapper;
import jrs.mystorage.util.mapper.ItemMapper;
import jrs.mystorage.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final PagedResourcesAssembler<Action> actionPagedResourcesAssembler;
    private final ItemMapper itemMapper;
    private final ProductMapper productMapper;
    private final ActionMapper actionMapper;
    private final EmployeeRepository employeeRepository;
    private final ActionRepository actionRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final StorageService storageService;

    @Override
    public void storeItemsInStorage(String employeeEmail, ArrayList<CItemDto> newItems) {

        Employee employee = employeeRepository.findByEmail(employeeEmail)
                .orElseThrow(NotFoundException::new);

        Storage storage = employee.getStorage();
        if (storage == null)
            throw new NotFoundException();

        Owner owner = storage.getOwner();
        if (owner == null)
            throw new NotFoundException();

        Action action = new Action();
        action.setAction(ActionType.STORE);
        action.setEmployee(employee);
        action.setStorage(storage);
        actionRepository.save(action);

        List<Item> collect = newItems.stream().map(cItemDto -> {
            Product product = null;

            if (cItemDto.getProductId() != null) {
                product = productRepository.findById(cItemDto.getProductId())
                        .orElseThrow(NotFoundException::new);
            } else {
                product = productMapper.toEntity(cItemDto.getNewProduct());
                product.setOwner(owner);
                productRepository.save(product);
            }

            Item item = itemMapper.toEntity(cItemDto);
            item.setProduct(product);
            item.setAction(action);
            return item;
        }).collect(Collectors.toList());

        itemRepository.saveAll(collect);

        storageService.storeItemsInStorage(storage.getId(), collect);
    }

    @Override
    public PagedModel<ActionDto> getAllStorageActions(String ownerEmail, UUID storageId, Pageable pageable) {

        Page<Action> actions = actionRepository
                .findAllByStorageIdAndStorageOwnerEmail(pageable, storageId, ownerEmail);
        return actionPagedResourcesAssembler.toModel(actions, actionMapper::toDto);
    }

    @Override
    public Page<Action> getAllEmployeeActions(String ownerEmail, UUID employeeId, Pageable pageable) {
        return actionRepository.findAllByEmployeeIdAndStorageOwnerEmail(pageable, employeeId, ownerEmail);
    }

    @Override
    public void removeItemsFromStorage(String employeeEmail, ArrayList<RemoveActionItemDto> removedItems) {

        Employee employee = employeeRepository.findByEmail(employeeEmail)
                .orElseThrow(NotFoundException::new);

        Storage storage = employee.getStorage();
        if (storage == null) throw new NotFoundException();

        Owner owner = employee.getOwner();
        if (owner == null) throw new NotFoundException();

        storageService.removeStorageItems(storage.getId(), removedItems);

        // Create action
        Action action = new Action();
        action.setAction(ActionType.REMOVE);
        action.setEmployee(employee);
        action.setStorage(storage);
        actionRepository.save(action);

        List<Item> removeActionItems = removedItems.stream().map(item -> {
            Item newItem = new Item();
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(NotFoundException::new);
            newItem.setAmount(item.getAmount());
            newItem.setProduct(product);
            newItem.setAction(action);
            return newItem;
        }).collect(Collectors.toList());

        itemRepository.saveAll(removeActionItems);
    }

    @Override
    public List<Action> findAllActionsByEmployeeId(UUID employeeId) {
        return actionRepository.findAllByEmployeeId(employeeId);
    }
}
