package jrs.mystorage.action.repository;

import jrs.mystorage.action.model.Action;
import jrs.mystorage.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActionRepository extends JpaRepository<Action, UUID> {

    Page<Action> findAllByStorageIdAndStorageOwnerEmail(UUID storageId, String ownerEmail, Pageable pageable );
}
