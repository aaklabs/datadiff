package com.aak.util.compare.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aak.util.compare.model.api.CompareResponse;

@Service
public class ExcelService {
	
	@Autowired
	private ExcelHelper helper;

	public InputStream generateExcelByteStream(CompareResponse compareResponse) throws DecoderException {
		ByteArrayInputStream in = helper.toExcelInputStream(compareResponse);
		return in;
	}

}