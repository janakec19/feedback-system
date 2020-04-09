package com.fms.processor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fms.processor.domain.EventParticipantInfo;
import com.fms.processor.domain.EventParticipantRegistry;
import com.fms.processor.domain.EventSummary;

public class ExcelUtil {

	private static final int ROW_INDEX = -1;

	private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd-MM-yy");

	public static EventParticipantInfo buildEventParticipantInfo(Row row) {
		EventParticipantInfo participantInfo = new EventParticipantInfo();
		int rowId=ROW_INDEX;
		participantInfo.setEventId(row.getCell(++rowId).toString());
		participantInfo.setBaseLocation(row.getCell(++rowId).toString());
		participantInfo.setBeneficiaryName(row.getCell(++rowId).toString());
		participantInfo.setCouncilName(row.getCell(++rowId).toString());
		participantInfo.setEventName(row.getCell(++rowId).toString());
		participantInfo.setEventDescription(row.getCell(++rowId).toString());
		try {
			participantInfo.setEventDate(FORMAT.parse(row.getCell(++rowId).toString()));
		} catch (ParseException e) {
			log.error("participantInfo event date set failed ,Exception due to {}", e.getMessage());
		}
		participantInfo.setEmployeeID(row.getCell(++rowId).toString());
		participantInfo.setEmployeeEmail(row.getCell(++rowId).toString());
		participantInfo.setEmployeeName(row.getCell(++rowId).toString());
		try {
			participantInfo.setVolunteerHours(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo VolunteerHours set failed , Exception due to {}", e.getMessage());
		}
		try {
			participantInfo.setTravelHours(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo setTravelHours failed , Exception due to {}", e.getMessage());
		}
		try {
			participantInfo.setLivesImpacted(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo setLivesImpacted failed , Exception due to {}", e.getMessage());
		}
		participantInfo.setBusinessUnit(row.getCell(++rowId).toString());
		participantInfo.setStatus(row.getCell(++rowId).toString());
		return participantInfo;
	}

	public static EventSummary buildEventSummary(Row row) {
		EventSummary summary = new EventSummary();
		int rowId=ROW_INDEX;
		summary.setEventID(row.getCell(++rowId).toString());
		summary.setMonth(row.getCell(++rowId).toString());
		summary.setBaseLocation(row.getCell(++rowId).toString());
		summary.setBeneficiaryName(row.getCell(++rowId).toString());
		summary.setVenueAddress(row.getCell(++rowId).toString());
		summary.setCouncilName(row.getCell(++rowId).toString());
		summary.setProject(row.getCell(++rowId).toString());
		summary.setCategory(row.getCell(++rowId).toString());
		summary.setEventName(row.getCell(++rowId).toString());
		summary.setEventDescription(row.getCell(++rowId).toString());
		try {
			summary.setEventDate(FORMAT.parse(row.getCell(++rowId).toString()));
		} catch (ParseException e) {
			log.error("summary event date set failed ,Exception due to {}", e.getMessage());
		}
		try {
			summary.setTotalVolunteers(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo setTotalVolunteers failed , Exception due to {}", e.getMessage());
		}

		try {
			summary.setTotalVolunteersHrs(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo setTotalVolunteersHrs failed , Exception due to {}", e.getMessage());
		}
		try {
			summary.setTotalTravelHrs(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo setTotalTravelHrs failed , Exception due to {}", e.getMessage());
		}
		try {
			summary.setOverAllVolunteeringHrs(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo setOverAllVolunteeringHrs failed , Exception due to {}", e.getMessage());
		}

		try {
			summary.setLivesImpacted(Double.valueOf(row.getCell(++rowId).toString()));
		} catch (NumberFormatException e) {
			log.error("participantInfo setLivesImpacted failed , Exception due to {}", e.getMessage());
		}

		summary.setActivityType(row.getCell(++rowId).toString());
		summary.setStatus(row.getCell(++rowId).toString());
		summary.setPOCId(row.getCell(++rowId).toString());
		summary.setPOCName(row.getCell(++rowId).toString());
		summary.setPOCContactNumber(row.getCell(++rowId).toString());
		return summary;
	}

	public static EventParticipantRegistry buildEventRegistry(Row row, boolean registered, boolean attended) {
		EventParticipantRegistry registry = new EventParticipantRegistry();
		int rowId=ROW_INDEX;
		registry.setEventId(row.getCell(++rowId).toString());
		registry.setEventName(row.getCell(++rowId).toString());
		registry.setBeneficiaryName(row.getCell(++rowId).toString());
		registry.setBaseLocation(row.getCell(++rowId).toString());
		try {
			registry.setEventDate(FORMAT.parse(row.getCell(++rowId).toString()));
		} catch (ParseException e) {
			log.error("registry event date set failed ,Exception due to {}", e.getMessage());
		}
		registry.setEmployeeID(row.getCell(++rowId).toString());
		registry.setAttended(attended);
		registry.setRegistered(registered);
		return registry;
	}
}
