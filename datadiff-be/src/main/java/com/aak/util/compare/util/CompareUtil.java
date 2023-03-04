package com.aak.util.compare.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompareUtil {
	
	final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	
	public String getTimestampInString() {
		LocalDateTime ldt = LocalDateTime.now();
		String formattedString = ldt.format(CUSTOM_FORMATTER);  //2022-12-09 18:25:58
		log.info("datetime ->{}",formattedString);
		return formattedString;
	}
}
