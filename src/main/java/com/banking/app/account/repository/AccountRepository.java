package com.banking.app.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.app.account.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {

}
