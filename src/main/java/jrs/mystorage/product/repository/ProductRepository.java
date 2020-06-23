package jrs.mystorage.product.repository;

import jrs.mystorage.employee.model.Employee;
import jrs.mystorage.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByOwnerId(UUID ownerId);
}
