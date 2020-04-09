package com.fms.processor.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class EventParticipantRegistry {

	@Id
	private String id;
	private String eventId;
	private String eventName;
	private String beneficiaryName;
	private String baseLocation;
	private Date eventDate;
	private String employeeID;
	private boolean registered;
	private boolean attended;
}
