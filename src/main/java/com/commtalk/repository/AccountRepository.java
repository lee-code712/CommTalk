package com.commtalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commtalk.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
