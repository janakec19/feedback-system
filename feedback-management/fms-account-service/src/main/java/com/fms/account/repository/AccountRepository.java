package com.fms.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.account.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}
