package com.fms.auth.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fms.auth.domain.AuthClientDetails;
import com.fms.auth.domain.User;
import com.fms.auth.repository.AuthClientRepository;
import com.fms.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	@Autowired
	private AuthClientRepository authClientRepository;
	
	public static void main(String[] args) {
		System.out.println(encoder.encode("12345"));
		//$2a$10$y6kk1TIYbAvcTmVkUBswneIMZ58lat7Nj5VG9xeyCbdW8tvTSJVQK

	}

	@Override
	public void create(User user) {

		Optional<User> existing = repository.findById(user.getUsername());
		existing.ifPresent(it -> {
			throw new IllegalArgumentException("user already exists: " + it.getUsername());
		});

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);

		repository.save(user);

		// once user is saved , we need to save the AuthClientDetails for the user
		authClientRepository.save(AuthClientDetails.builder().clientId(user.getUsername()).clientSecret(hash)
				.scopes("server").grantTypes("refresh_token,client_credentials").build());

		log.info("new user has been created: {}", user.getUsername());
	}
}
