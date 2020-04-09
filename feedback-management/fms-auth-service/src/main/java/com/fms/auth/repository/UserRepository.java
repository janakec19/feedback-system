package com.fms.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.auth.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
