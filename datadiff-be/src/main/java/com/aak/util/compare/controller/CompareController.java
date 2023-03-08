package com.aak.util.compare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aak.util.compare.diff.model.DBSource;
import com.aak.util.compare.model.DBRow;
import com.aak.util.compare.model.DatabaseSqlQueryRowResponse;
import com.aak.util.compare.model.api.CompareRequest;
import com.aak.util.compare.model.api.CompareResponse;
import com.aak.util.compare.model.api.DataRequest;
import com.aak.util.compare.model.api.DatagridCombinedResponse;
import com.aak.util.compare.model.api.RowCompareResult;
import com.aak.util.compare.service.CompareService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CompareController {

	@Autowired
	private CompareService service;

	@GetMapping(path = "/test")
	public List<RowCompareResult> getData() throws Exception {
		return service.compareSqlQuery("SELECT * FROM authors ", "SELECT * FROM authors2 limit 2", DBSource.DB1, DBSource.DB1);
	}
	
	@GetMapping(path = "/test/grid/column")
	public DatagridCombinedResponse getColumnDataAsGrid() throws Exception {
		return service.compareSqlQueryAndResponseAsGrid("SELECT * FROM authors", "SELECT * FROM authors2", DBSource.DB1, DBSource.DB1);
	}
	
	@GetMapping(path = "/test/grid/row")
	public CompareResponse getRowDataAsGrid() throws Exception {
		return service.compareSqlQueryAndResponseAsSingleGrid("SELECT * FROM authors", "SELECT * FROM authors2", DBSource.DB1, DBSource.DB1);
	}

	@PostMapping("/data")
	public DatabaseSqlQueryRowResponse getRecordsUsingSql(@RequestBody DataRequest dataRequest) throws Exception {
		log.info("Data Request->{}", dataRequest);
		return service.executeSqlQuery(dataRequest.getSqlQuery(), DBSource.valueOf(dataRequest.getDbSource()));
	}
	
	@PostMapping("/compare")
	public List<RowCompareResult> compareDataUsingSqlQuery(@RequestBody CompareRequest compareRequest) throws Exception {
		log.info("Compare Request->{}", compareRequest);
		return service.compareSqlQuery(compareRequest.getDb1SqlQuery(), compareRequest.getDb2SqlQuery(), DBSource.valueOf(compareRequest.getDb1Source()), DBSource.valueOf(compareRequest.getDb2Source()));
	}
	
	
	@PostMapping("/compare/datagrid/column")
	public DatagridCombinedResponse compareDataUsingSqlQueryToFlatGridResponse(@RequestBody CompareRequest compareRequest) throws Exception {
		log.info("Compare Request->{}", compareRequest);
		return service.compareSqlQueryAndResponseAsGrid(compareRequest.getDb1SqlQuery(), compareRequest.getDb2SqlQuery(), DBSource.valueOf(compareRequest.getDb1Source()), DBSource.valueOf(compareRequest.getDb2Source()));
	}
	
	@PostMapping("/compare/datagrid/row")
	public CompareResponse compareDataUsingSqlQueryToFlatRowGridResponse(@RequestBody CompareRequest compareRequest) throws Exception {
		log.info("Compare Request->{}", compareRequest);
		return service.compareSqlQueryAndResponseAsSingleGrid(compareRequest.getDb1SqlQuery(), compareRequest.getDb2SqlQuery(), DBSource.valueOf(compareRequest.getDb1Source()), DBSource.valueOf(compareRequest.getDb2Source()));
	}
}
