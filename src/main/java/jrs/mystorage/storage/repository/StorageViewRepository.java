package jrs.mystorage.storage.repository;

import jrs.mystorage.storage.model.StorageView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageViewRepository extends JpaRepository<StorageView, UUID> {

    Page<StorageView> findAll(Specification<StorageView> spec, Pageable pageable);
}
