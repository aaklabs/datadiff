package com.aak.util.compare.model.api;

import java.util.ArrayList;
import java.util.List;

public class GridRow {
	private List<GridRowColumn> rows;

	public GridRow() {
		super();
		this.rows = new ArrayList<>();
	}

	public List<GridRowColumn> getRows() {
		return rows;
	}

	@Override
	public String toString() {
		return "UIRow [rows=" + rows + "]";
	}
}
