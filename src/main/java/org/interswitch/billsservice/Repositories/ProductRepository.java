package org.interswitch.billsservice.Repositories;

import org.interswitch.billsservice.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBillerId(Long billerId);
}
