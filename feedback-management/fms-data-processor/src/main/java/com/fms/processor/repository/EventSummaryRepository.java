package com.fms.processor.repository;

import org.springframework.data.repository.CrudRepository;

import com.fms.processor.domain.EventSummary;

public interface EventSummaryRepository extends CrudRepository<EventSummary, String>  {

}
