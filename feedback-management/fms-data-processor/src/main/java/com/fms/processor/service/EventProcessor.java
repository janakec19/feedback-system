package com.fms.processor.service;

public interface EventProcessor {

	public void processEventParticipantInfo(String filePath);

	public void processEventSummary(String filePath);

	public void processParticipantNotAttended(String filePath);

	public void processParticipantUnregistered(String filePath);

}
