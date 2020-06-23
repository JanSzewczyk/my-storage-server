package jrs.mystorage.item.repository;

import jrs.mystorage.item.model.StorageItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageItemViewRepository extends JpaRepository<StorageItemView, UUID> {

    Page<StorageItemView> findAll(Specification<StorageItemView> spec, Pageable pageable);
}
