package com.aak.util.compare.model.api;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CompareResponse {
	
	private Set<String> columnNames;
	private List<GridRow> rows;

	public CompareResponse() {
		super();
		this.rows = new ArrayList<>();
		this.columnNames = new LinkedHashSet<>();
	}

	public List<GridRow> getRows() {
		return rows;
	}
	public Set<String> getColumnNames() {
		return columnNames;
	}

	@Override
	public String toString() {
		return "CompareResponse [columnNames=" + columnNames + ", rows=" + rows + "]";
	}

}
