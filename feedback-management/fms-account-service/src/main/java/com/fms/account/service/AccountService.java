package com.fms.account.service;

import com.fms.account.domain.Account;
import com.fms.account.domain.User;

public interface AccountService {

	/**
	 * Finds account by given name
	 *
	 * @param accountName
	 * @return found account
	 */
	Account findByName(String accountName);

	/**
	 * 
	 * @param user
	 * @return
	 */
	Account create(User user);

}
