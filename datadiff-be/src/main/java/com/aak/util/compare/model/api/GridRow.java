package com.aak.util.compare.model.api;

import java.util.ArrayList;
import java.util.List;

public class GridRow {
	private boolean isDifferent = false;
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

	public boolean isDifferent() {
		return isDifferent;
	}

	public void setDifferent(boolean isDifferent) {
		this.isDifferent = isDifferent;
	}
}
