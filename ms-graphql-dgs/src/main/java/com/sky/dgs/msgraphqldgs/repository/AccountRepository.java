package com.sky.dgs.msgraphqldgs.repository;

import com.sky.dgs.msgraphqldgs.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
