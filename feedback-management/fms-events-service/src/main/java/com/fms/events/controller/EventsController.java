package com.fms.events.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fms.events.client.AccountServiceClient;
import com.fms.events.domain.Account;
import com.fms.events.domain.Events;

@RestController
public class EventsController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AccountServiceClient accountServiceClient;

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public Events getEvent(@PathVariable String name) {
		Account acc=accountServiceClient.getAccountByName("jana");
		log.info("Account details  {}",acc.getName());
		return Events.builder().eventName(name).build();
	}

}
