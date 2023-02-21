package com.aak.util.compare.model.api;

import java.util.ArrayList;
import java.util.List;

public class RowCompareResult {
	private List<ColumnCompareResult> columns;

	@Override
	public String toString() {
		return "RowCompareResult [columns=" + columns + "]";
	}

	public RowCompareResult() {
		super();
		this.columns = new ArrayList<>();
	}

	public List<ColumnCompareResult> getColumns() {
		return columns;
	}
	
}
