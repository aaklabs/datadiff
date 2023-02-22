package com.aak.util.compare.model.api;

import java.util.ArrayList;
import java.util.List;

public class CompareResponse {
	private List<GridRow> rows;

	public CompareResponse() {
		super();
		this.rows = new ArrayList<>();
	}

	public List<GridRow> getRows() {
		return rows;
	}

	@Override
	public String toString() {
		return "CompareResponse [rows=" + rows + "]";
	}
}
