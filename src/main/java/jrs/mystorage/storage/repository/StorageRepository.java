package jrs.mystorage.storage.repository;

import jrs.mystorage.storage.model.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {

    Page<Storage> findAllByOwnerEmail(String ownerId, Pageable pageable);

    Optional<Storage> findByStorageIdAndOwnerEmail(UUID storageId, String ownerEmail);
}
