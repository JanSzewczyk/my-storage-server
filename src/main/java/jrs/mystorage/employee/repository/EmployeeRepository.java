package jrs.mystorage.employee.repository;

import jrs.mystorage.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);
    Page<Employee> findAllByOwnerEmail(String ownerEmail, Pageable pageable);
    Optional<Employee> findByEmployeeIdAndOwnerEmail(UUID employeeId, String ownerId);
}
