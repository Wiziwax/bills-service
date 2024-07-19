package org.interswitch.billsservice.Repositories;

import org.interswitch.billsservice.Entities.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Banks, Long> {
}
