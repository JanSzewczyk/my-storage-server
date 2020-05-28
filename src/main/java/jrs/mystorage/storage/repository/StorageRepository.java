package jrs.mystorage.storage.repository;

import jrs.mystorage.storage.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {

    List<Storage> findAllByOwnerEmail(String ownerId);

    Optional<Storage> findByStorageIdAndOwnerEmail(UUID storageId, String ownerEmail);
}
