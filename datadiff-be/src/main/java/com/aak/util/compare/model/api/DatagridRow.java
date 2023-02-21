package com.aak.util.compare.model.api;

import java.util.List;
import java.util.Objects;


public class DatagridRow {
	
	private int db1RowNo;
	private String db1ColumnName;
	private Object db1ColumnValue;
	private String db1DataType;
	
	private int db2RowNo;
	private String db2ColumnName;
	private Object db2ColumnValue;
	private String db2DataType;
	
	private boolean isDifferent = false;
	private boolean isOnlyInDb1 = false;
	private boolean isOnlyInDb2 = false;
	private boolean isNullInBoth= false;
	private List<String> comments = null;
	private boolean isError =false;
	
	public int getDb1RowNo() {
		return db1RowNo;
	}
	public void setDb1RowNo(int db1RowNo) {
		this.db1RowNo = db1RowNo;
	}
	public String getDb1ColumnName() {
		return db1ColumnName;
	}
	public void setDb1ColumnName(String db1ColumnName) {
		this.db1ColumnName = db1ColumnName;
	}
	public Object getDb1ColumnValue() {
		return db1ColumnValue;
	}
	public void setDb1ColumnValue(Object db1ColumnValue) {
		this.db1ColumnValue = db1ColumnValue;
	}
	public String getDb1DataType() {
		return db1DataType;
	}
	public void setDb1DataType(String db1DataType) {
		this.db1DataType = db1DataType;
	}
	public int getDb2RowNo() {
		return db2RowNo;
	}
	public void setDb2RowNo(int db2RowNo) {
		this.db2RowNo = db2RowNo;
	}
	public String getDb2ColumnName() {
		return db2ColumnName;
	}
	public void setDb2ColumnName(String db2ColumnName) {
		this.db2ColumnName = db2ColumnName;
	}
	public Object getDb2ColumnValue() {
		return db2ColumnValue;
	}
	public void setDb2ColumnValue(Object db2ColumnValue) {
		this.db2ColumnValue = db2ColumnValue;
	}
	public String getDb2DataType() {
		return db2DataType;
	}
	public void setDb2DataType(String db2DataType) {
		this.db2DataType = db2DataType;
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
		return "DatagridRow [db1RowNo=" + db1RowNo + ", db1ColumnName=" + db1ColumnName + ", db1ColumnValue="
				+ db1ColumnValue + ", db1DataType=" + db1DataType + ", db2RowNo=" + db2RowNo + ", db2ColumnName="
				+ db2ColumnName + ", db2ColumnValue=" + db2ColumnValue + ", db2DataType=" + db2DataType
				+ ", isDifferent=" + isDifferent + ", isOnlyInDb1=" + isOnlyInDb1 + ", isOnlyInDb2=" + isOnlyInDb2
				+ ", isNullInBoth=" + isNullInBoth + ", comments=" + comments + ", isError=" + isError + "]";
	}
	public DatagridRow(int db1RowNo, String db1ColumnName, Object db1ColumnValue, String db1DataType, int db2RowNo,
			String db2ColumnName, Object db2ColumnValue, String db2DataType, boolean isDifferent, boolean isOnlyInDb1,
			boolean isOnlyInDb2, boolean isNullInBoth, List<String> comments, boolean isError) {
		super();
		this.db1RowNo = db1RowNo;
		this.db1ColumnName = db1ColumnName;
		this.db1ColumnValue = db1ColumnValue;
		this.db1DataType = db1DataType;
		this.db2RowNo = db2RowNo;
		this.db2ColumnName = db2ColumnName;
		this.db2ColumnValue = db2ColumnValue;
		this.db2DataType = db2DataType;
		this.isDifferent = isDifferent;
		this.isOnlyInDb1 = isOnlyInDb1;
		this.isOnlyInDb2 = isOnlyInDb2;
		this.isNullInBoth = isNullInBoth;
		this.comments = comments;
		this.isError = isError;
	}
	public DatagridRow() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(comments, db1ColumnName, db1ColumnValue, db1DataType, db1RowNo, db2ColumnName,
				db2ColumnValue, db2DataType, db2RowNo, isDifferent, isError, isNullInBoth, isOnlyInDb1, isOnlyInDb2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatagridRow other = (DatagridRow) obj;
		return Objects.equals(comments, other.comments) && Objects.equals(db1ColumnName, other.db1ColumnName)
				&& Objects.equals(db1ColumnValue, other.db1ColumnValue)
				&& Objects.equals(db1DataType, other.db1DataType) && db1RowNo == other.db1RowNo
				&& Objects.equals(db2ColumnName, other.db2ColumnName)
				&& Objects.equals(db2ColumnValue, other.db2ColumnValue)
				&& Objects.equals(db2DataType, other.db2DataType) && db2RowNo == other.db2RowNo
				&& isDifferent == other.isDifferent && isError == other.isError && isNullInBoth == other.isNullInBoth
				&& isOnlyInDb1 == other.isOnlyInDb1 && isOnlyInDb2 == other.isOnlyInDb2;
	}
	
	

}
