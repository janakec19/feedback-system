package com.fms.account.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.account.client.AuthServiceClient;
import com.fms.account.domain.Account;
import com.fms.account.domain.User;
import com.fms.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuthServiceClient authClient;

	@Autowired
	private AccountRepository repository;

	@Override
	public Account findByName(String accountName) {
		return repository.findById(accountName).orElseThrow(
				()->new IllegalArgumentException("Account with name "+accountName+ " not exists."));
	}

	@Override
	public Account create(User user) {

		Optional<Account> existing = repository.findById(user.getUsername());
		existing.ifPresent(a -> {
			throw new IllegalArgumentException("Account with name " + a.getName() + " already exists.");
		});
		authClient.createUser(user);
		Account account = new Account();
		account.setName(user.getUsername());
		account.setCreatedDate(new Date());
		repository.save(account);

		log.info("new account has been created: " + account.getName());

		return account;
	}

}
