package com.fms.processor.repository;

import org.springframework.data.repository.CrudRepository;

import com.fms.processor.domain.EventParticipantInfo;

public interface EventParticipantInfoRepository extends CrudRepository<EventParticipantInfo, String>  {

}
