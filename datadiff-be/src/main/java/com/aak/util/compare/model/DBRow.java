package com.aak.util.compare.model;

import java.util.ArrayList;
import java.util.List;

public class DBRow {
	
	public DBRow() {
		super();
		this.columns = new ArrayList<>();
	}

	private List<DBColumn> columns = null;

	public List<DBColumn> getColumns() {
		return columns;
	}
}
