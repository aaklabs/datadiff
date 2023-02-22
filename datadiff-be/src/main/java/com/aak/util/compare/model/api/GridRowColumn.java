package com.aak.util.compare.model.api;

import java.util.List;
import java.util.Objects;

public class GridRowColumn {
	private int rowNo;
	private String columnName;
	private Object columnValue;
	private String dataType;
	private boolean isDifferent = false;
	private boolean isOnlyInDb1 = false;
	private boolean isOnlyInDb2 = false;
	private boolean isNullInBoth= false;
	private List<String> comments = null;
	private boolean isError =false;
	
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getColumnName() {
		return columnName;
	}
	public GridRowColumn() {
		super();
	}
	public GridRowColumn(int rowNo, String columnName, Object columnValue, String dataType, boolean isDifferent,
			boolean isOnlyInDb1, boolean isOnlyInDb2, boolean isNullInBoth, List<String> comments, boolean isError) {
		super();
		this.rowNo = rowNo;
		this.columnName = columnName;
		this.columnValue = columnValue;
		this.dataType = dataType;
		this.isDifferent = isDifferent;
		this.isOnlyInDb1 = isOnlyInDb1;
		this.isOnlyInDb2 = isOnlyInDb2;
		this.isNullInBoth = isNullInBoth;
		this.comments = comments;
		this.isError = isError;
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
	public boolean isDifferent() {
		return isDifferent;
	}
	public void setDifferent(boolean isDifferent) {
		this.isDifferent = isDifferent;
	}
	public boolean isOnlyInDb1() {
		return isOnlyInDb1;
	}
	public void setOnlyInDb1(boolean isOnlyInDb1) {
		this.isOnlyInDb1 = isOnlyInDb1;
	}
	public boolean isOnlyInDb2() {
		return isOnlyInDb2;
	}
	public void setOnlyInDb2(boolean isOnlyInDb2) {
		this.isOnlyInDb2 = isOnlyInDb2;
	}
	public boolean isNullInBoth() {
		return isNullInBoth;
	}
	public void setNullInBoth(boolean isNullInBoth) {
		this.isNullInBoth = isNullInBoth;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	@Override
	public String toString() {
		return "GridRowColumn [rowNo=" + rowNo + ", columnName=" + columnName + ", columnValue=" + columnValue
				+ ", dataType=" + dataType + ", isDifferent=" + isDifferent + ", isOnlyInDb1=" + isOnlyInDb1
				+ ", isOnlyInDb2=" + isOnlyInDb2 + ", isNullInBoth=" + isNullInBoth + ", comments=" + comments
				+ ", isError=" + isError + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(columnName, columnValue, comments, dataType, isDifferent, isError, isNullInBoth,
				isOnlyInDb1, isOnlyInDb2, rowNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridRowColumn other = (GridRowColumn) obj;
		return Objects.equals(columnName, other.columnName) && Objects.equals(columnValue, other.columnValue)
				&& Objects.equals(comments, other.comments) && Objects.equals(dataType, other.dataType)
				&& isDifferent == other.isDifferent && isError == other.isError && isNullInBoth == other.isNullInBoth
				&& isOnlyInDb1 == other.isOnlyInDb1 && isOnlyInDb2 == other.isOnlyInDb2 && rowNo == other.rowNo;
	}

}
