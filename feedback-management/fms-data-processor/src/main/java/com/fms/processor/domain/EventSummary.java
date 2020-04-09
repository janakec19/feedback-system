package com.fms.processor.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class EventSummary {

	@Id
	private String eventID;
	private String month;
	private String baseLocation;
	private String beneficiaryName;
	private String venueAddress;
	private String councilName;
	private String project;
	private String category;
	private String eventName;
	private String eventDescription;
	private Date eventDate;
	private Double totalVolunteers;
	private Double totalVolunteersHrs;
	private Double totalTravelHrs;
	private Double overAllVolunteeringHrs;
	private Double livesImpacted;
	private String activityType;
	private String status;
	private String POCId;
	private String POCName;
	private String POCContactNumber;

}
