package com.jhs.exam.exam2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ShortUri {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String shortCode;
	private String originUri;
	private String text;
	private String blanklessText;
	private int accessCount;
}