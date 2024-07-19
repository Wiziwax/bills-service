package org.interswitch.billsservice.Repositories;

import org.interswitch.billsservice.Entities.BillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillCategoryRepository extends JpaRepository<BillCategory, Long> {
}
