package com.aak.util.compare.model;

import java.util.Objects;

public class DBColumnMeta {
	private Integer colId;
	private String columnName;
	private String dataType;
	private String tableName;
	
	public DBColumnMeta(Integer colId, String columnName, String dataType, String tableName) {
		super();
		this.colId = colId;
		this.columnName = columnName;
		this.dataType = dataType;
		this.tableName = tableName;
	}

	public Integer getColId() {
		return colId;
	}

	public void setColId(Integer colId) {
		this.colId = colId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "DB1ColumnMeta [colId=" + colId + ", columnName=" + columnName + ", dataType=" + dataType
				+ ", tableName=" + tableName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(colId, columnName, dataType, tableName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DBColumnMeta other = (DBColumnMeta) obj;
		return Objects.equals(colId, other.colId) && Objects.equals(columnName, other.columnName)
				&& Objects.equals(dataType, other.dataType) && Objects.equals(tableName, other.tableName);
	}
}
