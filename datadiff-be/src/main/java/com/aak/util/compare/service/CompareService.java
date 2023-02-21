package com.aak.util.compare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aak.util.compare.diff.CompareManager;
import com.aak.util.compare.diff.model.DBSource;
import com.aak.util.compare.model.DBRow;
import com.aak.util.compare.model.api.ColumnCompareResult;
import com.aak.util.compare.model.api.DatagridCombinedResponse;
import com.aak.util.compare.model.api.DatagridRow;
import com.aak.util.compare.model.api.RowCompareResult;

@Service
public class CompareService {

	@Autowired
	private DBRepositoryFactory factory;
	
	@Autowired
	private CompareManager comparator;
	
	public List<RowCompareResult> compareSqlQuery(String db1Sql, String db2Sql , DBSource db1Source, DBSource db2Source) throws Exception {
		List<DBRow> db1Records = factory.getRepo(db1Source).getRecords(db1Sql);
		List<DBRow> db2Records = factory.getRepo(db2Source).getRecords(db2Sql);
		List<RowCompareResult> response = comparator.compareItem(db1Records, db2Records);
		return response;
	}
	
	public DatagridCombinedResponse compareSqlQueryAndResponseAsGrid(String db1Sql, String db2Sql , DBSource db1Source, DBSource db2Source) throws Exception {
		List<DBRow> db1Records = factory.getRepo(db1Source).getRecords(db1Sql);
		List<DBRow> db2Records = factory.getRepo(db2Source).getRecords(db2Sql);
		List<RowCompareResult> response = comparator.compareItem(db1Records, db2Records);
		
		DatagridCombinedResponse gridResponse = new DatagridCombinedResponse();
		
		for (RowCompareResult columns : response) {
			for (ColumnCompareResult column : columns.getColumns()) {
				DatagridRow row = new DatagridRow(column.getDb1().getRowNo(), column.getDb1().getColumnName(), column.getDb1().getColumnValue(), column.getDb1().getDataType(), column.getDb2().getRowNo(), column.getDb2().getColumnName(), column.getDb2().getColumnValue(), column.getDb2().getDataType(),
						column.isDifferent(), column.isOnlyInDb1(), column.isOnlyInDb2(), column.isNullInBoth(), column.getComments(), column.isError());
				gridResponse.getRows().add(row);
			}
		}
		
		return gridResponse;
	}
	
	public List<DBRow> executeSqlQuery(String sql, DBSource dbSource) throws Exception {
		List<DBRow> dbRecords = factory.getRepo(dbSource).getRecords(sql);
		return dbRecords;
	}
}
