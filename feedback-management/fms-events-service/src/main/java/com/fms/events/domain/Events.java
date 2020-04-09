package com.fms.events.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Events {

	private String eventName;
	
	private String eventDescription;
}
