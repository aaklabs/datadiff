package com.aak.util.compare.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBRow {
	private boolean dummyRow;
	
	public DBRow(boolean dummyRow) {
		super();
		this.columns = new ArrayList<>();
		this.dummyRow = dummyRow;
	}

	private List<DBColumn> columns = null;

	public List<DBColumn> getColumns() {
		return columns;
	}
	public boolean isDummyRow() {
		return dummyRow;
	}
	@Override
	public String toString() {
		return "DBRow [dummyRow=" + dummyRow + ", columns=" + columns + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(columns, dummyRow);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DBRow other = (DBRow) obj;
		return Objects.equals(columns, other.columns) && dummyRow == other.dummyRow;
	}
	
}
