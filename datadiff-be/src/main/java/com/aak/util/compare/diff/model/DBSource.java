package com.aak.util.compare.diff.model;

public enum DBSource {
	DB1("DB1"),
	DB2("DB2");
	
	private String label;
	
	DBSource(String label){
		this.label = label;
	}
}
