package com.aak.util.compare.model.api;

import java.util.Objects;

public class CompareRequest {
	private String db1SqlQuery;
	private String db1Source;
	private String db2SqlQuery;
	private String db2Source;
	
	public CompareRequest() {
		super();
	}
	public CompareRequest(String db1SqlQuery, String db1Source, String db2SqlQuery, String db2Source) {
		super();
		this.db1SqlQuery = db1SqlQuery;
		this.db1Source = db1Source;
		this.db2SqlQuery = db2SqlQuery;
		this.db2Source = db2Source;
	}
	public String getDb1SqlQuery() {
		return db1SqlQuery;
	}
	public void setDb1SqlQuery(String db1SqlQuery) {
		this.db1SqlQuery = db1SqlQuery;
	}
	public String getDb1Source() {
		return db1Source;
	}
	public void setDb1Source(String db1Source) {
		this.db1Source = db1Source;
	}
	public String getDb2SqlQuery() {
		return db2SqlQuery;
	}
	public void setDb2SqlQuery(String db2SqlQuery) {
		this.db2SqlQuery = db2SqlQuery;
	}
	public String getDb2Source() {
		return db2Source;
	}
	public void setDb2Source(String db2Source) {
		this.db2Source = db2Source;
	}
	@Override
	public String toString() {
		return "CompareRequest [db1SqlQuery=" + db1SqlQuery + ", db1Source=" + db1Source + ", db2SqlQuery="
				+ db2SqlQuery + ", db2Source=" + db2Source + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(db1Source, db1SqlQuery, db2Source, db2SqlQuery);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompareRequest other = (CompareRequest) obj;
		return Objects.equals(db1Source, other.db1Source) && Objects.equals(db1SqlQuery, other.db1SqlQuery)
				&& Objects.equals(db2Source, other.db2Source) && Objects.equals(db2SqlQuery, other.db2SqlQuery);
	}
	
}
