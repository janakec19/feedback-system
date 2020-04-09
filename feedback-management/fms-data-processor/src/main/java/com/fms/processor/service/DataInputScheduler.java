package com.fms.processor.service;

import java.io.File;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataInputScheduler {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${event.info.filepath}")
	private String eventInfoFilePath;

	@Value("${event.summary.filepath}")
	private String eventSummaryFilePath;

	@Value("${event.not-attended.filepath}")
	private String volunteerNotAttendedFilePath;

	@Value("${event.unregistered.filepath}")
	private String volunteerUnregisteredFilePath;

	@Autowired
	private EventProcessor processor;

	@Scheduled(cron = "${data-input.cron}")
	public void inputListener() {
		log.info("looking for input files");
		
		isFileExists(eventInfoFilePath).ifPresent(b -> {
			processor.processEventParticipantInfo(eventInfoFilePath);
		});
		isFileExists(eventSummaryFilePath).ifPresent(b -> {
			processor.processEventSummary(eventSummaryFilePath);
		});
		isFileExists(volunteerNotAttendedFilePath).ifPresent(b -> {
			processor.processParticipantNotAttended(volunteerNotAttendedFilePath);
		});
		isFileExists(volunteerUnregisteredFilePath).ifPresent(b -> {
			processor.processParticipantUnregistered(volunteerUnregisteredFilePath);
		});

	}

	private Optional<Boolean> isFileExists(String path) {
		File f = new File(path);
		return (f.exists() && !f.isDirectory()) ? Optional.of(Boolean.TRUE) : Optional.empty();
	}
}
