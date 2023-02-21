package com.aak.util.compare.model.api;

import java.util.Objects;

public class DataRequest {
	private String sqlQuery;
	private String dbSource;
	
	public DataRequest() {
		super();
	}
	
	public DataRequest(String sqlQuery, String dbSource) {
		super();
		this.sqlQuery = sqlQuery;
		this.dbSource = dbSource;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dbSource, sqlQuery);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataRequest other = (DataRequest) obj;
		return Objects.equals(dbSource, other.dbSource) && Objects.equals(sqlQuery, other.sqlQuery);
	}
	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	public String getDbSource() {
		return dbSource;
	}
	public void setDbSource(String dbSource) {
		this.dbSource = dbSource;
	}
	@Override
	public String toString() {
		return "CompareRequest [sqlQuery=" + sqlQuery + ", dbSource=" + dbSource + "]";
	}
}
