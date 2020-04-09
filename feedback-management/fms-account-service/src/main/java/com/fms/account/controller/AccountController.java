package com.fms.account.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fms.account.domain.Account;
import com.fms.account.domain.User;
import com.fms.account.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public Account getAccountByName(@PathVariable String name,Principal principal) {
		System.out.println(principal.getName());
		return accountService.findByName(name);
	}

	@RequestMapping(path = "/current", method = RequestMethod.GET)
	public Account getCurrentAccount(Principal principal) {
		return accountService.findByName(principal.getName());
	}

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public Account createNewAccount(@Valid @RequestBody User user) {
		return accountService.create(user);
	}
	
}
