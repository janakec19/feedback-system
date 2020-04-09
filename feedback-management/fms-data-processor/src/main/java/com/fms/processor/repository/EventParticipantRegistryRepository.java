package com.fms.processor.repository;

import org.springframework.data.repository.CrudRepository;

import com.fms.processor.domain.EventParticipantRegistry;

public interface EventParticipantRegistryRepository extends CrudRepository<EventParticipantRegistry, String>  {

}
