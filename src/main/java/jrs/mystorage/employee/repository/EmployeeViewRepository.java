package jrs.mystorage.employee.repository;

import jrs.mystorage.employee.model.EmployeeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeViewRepository extends JpaRepository<EmployeeView, UUID> {

    Page<EmployeeView> findAll(Specification<EmployeeView> spec, Pageable pageable);
}
