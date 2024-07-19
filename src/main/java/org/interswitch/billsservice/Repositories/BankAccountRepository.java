package org.interswitch.billsservice.Repositories;

import org.interswitch.billsservice.Entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Page<BankAccount> findAllByBankCode(String bankCode, Pageable pageable);

    Optional<BankAccount> findByAccountNo(String accountNo);
}