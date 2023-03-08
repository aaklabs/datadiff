package com.aak.util.compare.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DatabaseSqlQueryRowResponse {
	Set<DBColumnMeta> columnNames = null;
	List<DBRow> dbRows = null;
	int columnCount=0;
	int rows;
	
	public DatabaseSqlQueryRowResponse(int columnCount, int rows) {
		super();
		this.dbRows = new ArrayList<>();
		this.columnCount = columnCount;
		this.columnNames = new HashSet<>();
		this.rows = rows;
	}

	public List<DBRow> getDbRows() {
		return dbRows;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getColumns() {
		return columnCount;
	}

	public int getRows() {
		return rows;
	}

	public Set<DBColumnMeta> getColumnNames() {
		return columnNames;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columnCount, columnNames, dbRows, rows);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseSqlQueryRowResponse other = (DatabaseSqlQueryRowResponse) obj;
		return columnCount == other.columnCount && Objects.equals(columnNames, other.columnNames)
				&& Objects.equals(dbRows, other.dbRows) && rows == other.rows;
	}

	@Override
	public String toString() {
		return "DatabaseSqlQueryRowResponse [columnNames=" + columnNames + ", dbRows=" + dbRows + ", columnCount="
				+ columnCount + ", rows=" + rows + "]";
	}


}
