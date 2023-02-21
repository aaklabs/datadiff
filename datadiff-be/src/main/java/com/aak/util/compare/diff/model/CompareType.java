package com.aak.util.compare.diff.model;

public enum CompareType {
	CHAR("CHAR"),
	STRING("STRING"),
	LONG("LONG"),
	BIGDECIMAL("BIGDECIMAL"),
	DATE("DATE"),
	DATETIME("DATETIME"),
	IGNORE("IGNORE");
	
	private String label;
	
	CompareType(String label){
		this.label = label;
	}
}
