package com.fms.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.auth.domain.AuthClientDetails;

@Repository
public interface AuthClientRepository extends CrudRepository<AuthClientDetails, String> {

	Optional<AuthClientDetails> findByClientId(String clientId);

}
