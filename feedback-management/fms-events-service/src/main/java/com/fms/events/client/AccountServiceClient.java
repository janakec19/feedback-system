package com.fms.events.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fms.events.domain.Account;

@FeignClient(name = "fms-account-service")
public interface AccountServiceClient {

	@RequestMapping(path = "/accounts/{name}", method = RequestMethod.GET)
	public Account getAccountByName(@PathVariable String name) ;
	
}
