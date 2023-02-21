package com.aak.util.compare.model.api;

import java.util.ArrayList;
import java.util.List;

public class DatagridCombinedResponse {
	private List<DatagridRow> rows;

	public DatagridCombinedResponse() {
		super();
		this.rows = new ArrayList<>();
	}

	public List<DatagridRow> getRows() {
		return rows;
	}

	@Override
	public String toString() {
		return "DatagridCombinedResponse [rows=" + rows + "]";
	}
}
