package jrs.mystorage.item.repository;

import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.item.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    Optional<Item> findByStorageIdAndProductId(UUID storageId, UUID productId);

    Page<Item> findAllByStorageIdAndStorageOwnerEmail(UUID storageId, String ownerEmail, Pageable pageable);

    Page<Item> findAllByActionStorageIdAndActionStorageOwnerEmailOrderByActionCreatedAtDesc(UUID storageId, String ownerEmail, Pageable pageable);

    List<Item> findAllByStorageId(UUID storageId);
}
