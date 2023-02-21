package com.aak.util.compare.model;

import java.util.Objects;

public class DBColumn {
	private int rowNo;
	private String columnName;
	private Object columnValue;
	private String dataType;
	private String tableName;
	
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Object getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(Object columnValue) {
		this.columnValue = columnValue;
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
	public int hashCode() {
		return Objects.hash(columnName, columnValue, dataType, rowNo, tableName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DBColumn other = (DBColumn) obj;
		return Objects.equals(columnName, other.columnName) && Objects.equals(columnValue, other.columnValue)
				&& Objects.equals(dataType, other.dataType) && rowNo == other.rowNo
				&& Objects.equals(tableName, other.tableName);
	}
	@Override
	public String toString() {
		return "DBColumn [rowNo=" + rowNo + ", columnName=" + columnName + ", columnValue=" + columnValue + ", dataType="
				+ dataType + ", tableName=" + tableName + "]";
	}
	public DBColumn(int rowNo, String columnName, Object columnValue, String dataType, String tableName) {
		super();
		this.rowNo = rowNo;
		this.columnName = columnName;
		this.columnValue = columnValue;
		this.dataType = dataType;
		this.tableName = tableName;
	}
	
}