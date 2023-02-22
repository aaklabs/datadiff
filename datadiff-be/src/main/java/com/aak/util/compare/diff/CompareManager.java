package com.aak.util.compare.diff;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.aak.util.compare.model.DBColumn;
import com.aak.util.compare.model.DBRow;
import com.aak.util.compare.model.api.ColumnCompareResult;
import com.aak.util.compare.model.api.RowCompareResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompareManager {
	
	public ColumnCompareResult compareItem(DBColumn db1RowColRecord, DBColumn db2RowColRecord) {
		log.debug("Query1 Record -> {} ", db1RowColRecord);
		log.debug("Query2 Record -> {} ", db2RowColRecord);
		
		ColumnCompareResult compareResultItem = new ColumnCompareResult();
		compareResultItem.setDb1(db1RowColRecord);
		compareResultItem.setDb2(db2RowColRecord);
		
		
		if(db1RowColRecord == null && db2RowColRecord == null) {
			log.debug("Null data found for Query1 or Query2 Records");
			compareResultItem.setNullInBoth(true);
			compareResultItem.setError(true);
			return compareResultItem;
		}
		else if(db1RowColRecord == null || db2RowColRecord == null) {
			log.debug("Null data found for Query1 or Query2 Records");
			compareResultItem.setNullInBoth(true);
			compareResultItem.setError(true);
			return compareResultItem;
		}
		
		final Object acutalValue = db1RowColRecord.getColumnValue();
		final Object expectValue = db2RowColRecord.getColumnValue();
		final String actualDataType = db1RowColRecord.getDataType();
		final String expectDataType = db1RowColRecord.getDataType();
				
				
		
		if(Objects.isNull(acutalValue) && Objects.isNull(expectValue)){
			// both are empty/null
			// to avoid post null operations
			log.debug("Null Values found for Query1 and Query2 Records");
			compareResultItem.setNullInBoth(false);
			compareResultItem.setError(true);
			return compareResultItem;
		}
		else if(Objects.isNull(acutalValue) && !Objects.isNull(expectValue)){
			log.debug("Null Values found for Query1 or Query2 Records");
			compareResultItem.setOnlyInDb2(true);
			compareResultItem.setError(true);
			return compareResultItem;
		}
		else if(!Objects.isNull(acutalValue) && Objects.isNull(expectValue)){
			log.debug("Null Values found for Query1 or Query2 Records");
			compareResultItem.setOnlyInDb1(true);
			compareResultItem.setError(true);
			return compareResultItem;
		}
		
		if (!actualDataType.equals(expectDataType)) {
			log.debug("Data Type not matching for actual & expected columns");
			compareResultItem.setDifferent(true);
			compareResultItem.setError(true);
			return compareResultItem;
		}
		else if(Objects.isNull(actualDataType) || Objects.isNull(expectDataType)){
			log.debug("Invalid or Null Data Type found for actual & expected columns");
			compareResultItem.setDifferent(true);
			compareResultItem.setError(true);
			return compareResultItem;
		}
		
		// same
		boolean isEqual = acutalValue.toString().equals(expectValue.toString());
		
		if(isEqual) {
			compareResultItem.setDifferent(false);
		}
		else {
			compareResultItem.setDifferent(true);
			compareResultItem.setError(true);
		}
		
		//StringUtils.join(row) + (unmatched ? " - UNMATCHED" : " - MATCHED");
		return compareResultItem;
	}

	//db1 has more rows than db2
	//db1 has more col than db2
	public List<RowCompareResult> compareItem(List<DBRow> db1Rows, List<DBRow> db2Rows) {
		int db1RowCount = db1Rows.size();
		int db2RowCount = db2Rows.size();
		
		if(db1RowCount != db2RowCount) {
			log.error("Data row count not maching for db1Rows ->{} and db2Rows ->{}",db1RowCount,db2RowCount);
		}
		if(db1RowCount ==0 || db2RowCount == 0) {
			log.error("Data row count Zero for db1Rows ->{} or db2Rows ->{}",db1RowCount,db2RowCount);
		}
		
		List<RowCompareResult> compareResult = new ArrayList<>();
		
		for(int i=0; i < db1RowCount; i++) {
			DBRow db1RowRecord = null;
			DBRow db2RowRecord = null;
			try {
				db1RowRecord = db1Rows.get(i);
				db2RowRecord = db2Rows.get(i);
				
				int db1RowItemColumnCount = db1RowRecord.getColumns().size();
				int db2RowItemColumnCount = db2RowRecord.getColumns().size();
				
				if(db1RowItemColumnCount != db2RowItemColumnCount) {
					String msg ="Column count not maching for db1RowRecord ->"+db1RowItemColumnCount+" and db2RowRecord ->"+db2RowItemColumnCount; 
					log.error(msg);
				}
				if(db1RowItemColumnCount ==0 || db2RowItemColumnCount == 0) {
					log.error("Column count zero for db1RowRecord ->{} or db2RowRecord ->{}",db1RowItemColumnCount,db2RowItemColumnCount);
				}
				
				RowCompareResult rowCompareResult = new RowCompareResult();
				log.info("db1RowItemColumnCount ->{} db2RowItemColumnCount->{}",db1RowItemColumnCount,db2RowItemColumnCount );
				
				for (int j = 0; j < db1RowItemColumnCount; j++) {
					DBColumn db1RowColRecord = null;
					DBColumn db2RowColRecord = null;
					try {
						db1RowColRecord = db1RowRecord.getColumns().get(j);
						db2RowColRecord = db2RowRecord.getColumns().get(j);
						ColumnCompareResult compareResultItem = compareItem(db1RowColRecord, db2RowColRecord);
						rowCompareResult.getColumns().add(compareResultItem);
					} catch (Exception e) {
						log.error("Column not found Db2 IndexOutOfBoundsException -> {}",e.getMessage());
					}
				}
				compareResult.add(rowCompareResult);
				
				
			} catch (IndexOutOfBoundsException e) {
				log.error("Rows not found in Db2 IndexOutOfBoundsException -> {}",e.getMessage());
			}
		}
		
		return compareResult;
	}

}
