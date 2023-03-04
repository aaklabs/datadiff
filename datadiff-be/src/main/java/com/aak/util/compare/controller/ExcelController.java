package com.aak.util.compare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aak.util.compare.diff.model.DBSource;
import com.aak.util.compare.model.api.CompareRequest;
import com.aak.util.compare.model.api.CompareResponse;
import com.aak.util.compare.service.CompareService;
import com.aak.util.compare.service.ExcelService;
import com.aak.util.compare.util.CompareUtil;

import lombok.extern.slf4j.Slf4j;

//@CrossOrigin("http://localhost:8081")
@Controller
//@RequestMapping("/api/excel")
@Slf4j
public class ExcelController {

	@Autowired
	private CompareService service;
	
	@Autowired
	private CompareUtil util;

	@Autowired
	private ExcelService fileService;

	@PostMapping("/compare/datagrid/excel/download")
	public ResponseEntity<Resource> compareDataUsingSqlQueryToFlatRowGridResponse(@RequestBody CompareRequest compareRequest) throws Exception {
		log.info("Compare Request->{}", compareRequest);
		CompareResponse compareResponse = service.compareSqlQueryAndResponseAsSingleGrid(compareRequest.getDb1SqlQuery(),compareRequest.getDb2SqlQuery(), DBSource.valueOf(compareRequest.getDb1Source()),DBSource.valueOf(compareRequest.getDb2Source()));
		String filename = "comparex-utility_"+util.getTimestampInString()+".xlsx";
		log.info("filename -> {}",filename);
		InputStreamResource file = new InputStreamResource(fileService.generateExcelByteStream(compareResponse));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

}