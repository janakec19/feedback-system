package com.fms.processor.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class EventParticipantInfo {

	@Id
	private String id;
	private String eventId;
	private String baseLocation;
	private String beneficiaryName;
	private String councilName;
	private String eventName;
	private String eventDescription;
	private Date eventDate;
	private String employeeID;
	private String employeeEmail;
	private String employeeName;
	private Double volunteerHours;
	private Double travelHours;
	private Double livesImpacted;
	private String businessUnit;
	private String status;
}
