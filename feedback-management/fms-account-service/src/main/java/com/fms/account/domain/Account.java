package com.fms.account.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	private String name;
	
	private String firstName;
	
	private String lastName;
	
	private String email;

	private Date createdDate;
	
	private Date updatedDate;

}
