package com.aak.util.compare.repo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.aak.util.compare.model.DBColumn;
import com.aak.util.compare.model.DBRow;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public abstract class DBRepo {

	private JdbcTemplate jdbcTemplate = null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
	
	public DBRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	
	
	public int deleteAll(String sql) {
		return jdbcTemplate.update(sql);
	}
	
	public int updateRecord(String sql) {
		int count = 0;
		try {
			count = jdbcTemplate.update(sql);
		} catch (Exception e) {
			log.error("updateRecord failed -> {} {}", System.lineSeparator() , sql);
			log.error(e.getMessage(),e);
		}
		return count;
	}

	// it will return all data from table with column name as key and value as
	// columndata
	public List<Map<String, Object>> useResultSetMetaData(String sql) {
		return jdbcTemplate.queryForList(sql);

	}
	
	public List<Map<String, Object>> useSqlQuery(String sql) {
		List<Map<String, Object>> recordList = null;
		recordList = jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int index) throws SQLException {
				Map<String, Object> recordMap = new HashMap<>();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					recordMap.put(columnName, rs.getObject(columnName));
				}
				return recordMap;
			}
		});
		return recordList;
	}
	
	public List<Map<String, DBColumn>> useSqlQueryToGetRecord(String sql) {
		List<Map<String, DBColumn>> recordList = null;
		recordList = jdbcTemplate.query(sql, new RowMapper<Map<String, DBColumn>>() {
			@Override
			public Map<String, DBColumn> mapRow(ResultSet rs, int index) throws SQLException {
				Map<String, DBColumn> recordMap = new HashMap<>();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				int row = 1;
				for (int i = 1; i <= columnCount; i++) {
					String columnName 	= metaData.getColumnName(i);
					Object columnValue 	= rs.getObject(i);
					String columnTypeName = metaData.getColumnTypeName(i);
					String tableName = metaData.getTableName(i);
					DBColumn r = new DBColumn(row, columnName, columnValue, columnTypeName, tableName);
					  
					recordMap.put(columnName,r);
				}
				row++;
				return recordMap;
			}
		});
		return recordList;
	}
	
	public List<DBRow> getRecords(String sql) {
		List<DBRow> rows = new ArrayList<>();
		List<Map<String, DBColumn>> data= useSqlQueryToGetRecord(sql);
		for (Map<String, DBColumn> map : data) {
			DBRow row = new DBRow();
			for (DBColumn recordItem : map.values()) {
				row.getColumns().add(recordItem);
			}
			rows.add(row);
		}
		return rows;
	}
	
	//https://www.mockaroo.com/
	public List<Map<String, DBColumn>> getRecordsUsingSqlQuery(String sql) {
		List<Map<String, DBColumn>> recordList = null;
		
	    final AtomicInteger integerCounter = new AtomicInteger(0);

		recordList = jdbcTemplate.query(sql, new RowMapper<Map<String, DBColumn>>() {
			int count=0;
			@Override
			public Map<String, DBColumn> mapRow(ResultSet rs, int index) throws SQLException {
				Map<String, DBColumn> recordMap = new HashMap<>();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 0; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					recordMap.put(columnName, new DBColumn(integerCounter.incrementAndGet(), metaData.getColumnName(index), rs.getObject(i), metaData.getColumnTypeName(i), metaData.getTableName(i)));
				}
				return recordMap;
			}
		});
		return recordList;
	}
}
