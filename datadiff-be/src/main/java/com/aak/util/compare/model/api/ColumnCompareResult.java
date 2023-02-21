package com.aak.util.compare.model.api;

import java.util.List;

import com.aak.util.compare.model.DBColumn;

public class ColumnCompareResult {

	private DBColumn db1;
	private DBColumn db2;
	private boolean isDifferent = false;
	private boolean isOnlyInDb1 = false;
	private boolean isOnlyInDb2 = false;
	private boolean isNullInBoth= false;
	private List<String> comments = null;
	private boolean isError =false;
	
	public DBColumn getDb1() {
		return db1;
	}
	public void setDb1(DBColumn db1) {
		this.db1 = db1;
	}
	public DBColumn getDb2() {
		return db2;
	}
	public void setDb2(DBColumn db2) {
		this.db2 = db2;
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
	public List<String> getComments() {
		return comments;
	}
	public boolean isNullInBoth() {
		return isNullInBoth;
	}
	public void setNullInBoth(boolean isNullInBoth) {
		this.isNullInBoth = isNullInBoth;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	@Override
	public String toString() {
		return "ColumnCompareResult [db1=" + db1 + ", db2=" + db2 + ", isDifferent=" + isDifferent + ", isOnlyInDb1="
				+ isOnlyInDb1 + ", isOnlyInDb2=" + isOnlyInDb2 + ", isNullInBoth=" + isNullInBoth + ", comments="
				+ comments + ", isError=" + isError + "]";
	}
}
