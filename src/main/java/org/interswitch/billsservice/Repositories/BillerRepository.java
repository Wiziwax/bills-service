package org.interswitch.billsservice.Repositories;

import org.interswitch.billsservice.Entities.Biller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillerRepository extends JpaRepository<Biller, Long> {
    List<Biller> findByBillCategoryId(Long billCategoryId);
}
