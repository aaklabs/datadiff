package com.aak.util.compare.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aak.util.compare.model.api.CompareResponse;
import com.aak.util.compare.model.api.GridRow;
import com.aak.util.compare.model.api.GridRowColumn;
import com.aak.util.compare.util.CompareUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	@Autowired
	public CompareUtil util;

	public ByteArrayInputStream toExcelInputStream(CompareResponse compareResponse) throws DecoderException {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			generateExcelSheet(compareResponse, workbook, true, "All rows");
			generateExcelSheet(compareResponse, workbook, false, "Mismatched");

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

	private void generateExcelSheet(CompareResponse compareResponse, Workbook workbook, boolean show_all_data, String sheetName) throws DecoderException {
		Sheet completeDataSheet = workbook.createSheet(sheetName);
		// Header start
		addHeaderToWorksheet(completeDataSheet, compareResponse.getColumnNames());
		// Header end
		
		int rowIdx = 1;
		for (GridRow gridRowItem : compareResponse.getRows()) {
			if(show_all_data) {
				createExcelRow(completeDataSheet, rowIdx, gridRowItem);
			}
			else {
				if(gridRowItem.isDifferent()) {
					createExcelRow(completeDataSheet, rowIdx, gridRowItem);	
				}
			}
			rowIdx++;
		}
	}

	private void createExcelRow(Sheet completeDataSheet, int rowIdx, GridRow gridRowItem) throws DecoderException {
		Row row = completeDataSheet.createRow(rowIdx);
		
		Cell statusColumnCell = row.createCell(0);
		statusColumnCell.setCellValue(gridRowItem.isDifferent() ? "MISMATCH": "MATCH");
		
		if(gridRowItem.isDifferent()) {
			statusColumnCell.setCellStyle(getBadRowCellStyle(completeDataSheet));
		}
		
		int colIdx = 1;
		for (GridRowColumn colValue : gridRowItem.getRows()) {
			final Cell excelCell = row.createCell(colIdx++);
			if (colValue.isDifferent()) {
				XSSFCellStyle badCellStyle = getBadCellStyle(completeDataSheet);
				excelCell.setCellValue(colValue.getColumnValue().toString());
				excelCell.setCellStyle(badCellStyle);
			} else {
				excelCell.setCellValue(colValue.getColumnValue().toString());
				
				if(gridRowItem.isDifferent()) {
					//row highlight for bad records
					excelCell.setCellStyle(getBadRowCellStyle(completeDataSheet));
				}
				
			}
		}
	}

	private void addHeaderToWorksheet(Sheet completeDataSheet, Set<String> columnNames) {
		log.info("Generate Headers");
		final Row headerRow = completeDataSheet.createRow(0);
		
		Cell statusHeaderColumnCell = headerRow.createCell(0);
		statusHeaderColumnCell.setCellValue("STATUS");
		
		int cellCounter = 1;
		for (String columnName : columnNames) {
			Cell cell = headerRow.createCell(cellCounter);
			cell.setCellValue(columnName);
			cellCounter++;
		}
	}

	private XSSFCellStyle getBadCellStyle(Sheet completeDataSheet) throws DecoderException {
		// bad red background
		String rgbBadFontColor = "9C0006";
		byte[] rgbBadFont = Hex.decodeHex(rgbBadFontColor); // get byte array from hex string

		String rgbBadColor = "FFC7CE";
		byte[] rgbB = Hex.decodeHex(rgbBadColor); // get byte array from hex string
		XSSFColor badColor = new XSSFColor(rgbB, null); // IndexedColorMap has no usage until now. So it can be set
														// null.

		XSSFCellStyle cellStyle = (XSSFCellStyle) completeDataSheet.getWorkbook().createCellStyle();
		cellStyle.setFillForegroundColor(badColor);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Font font = completeDataSheet.getWorkbook().createFont();
		XSSFFont xssfFont = (XSSFFont) font;
		xssfFont.setColor(new XSSFColor(rgbBadFont, null));
		font.setBold(false);

		cellStyle.setFont(font);

		CellStyle headerCellStyle = completeDataSheet.getWorkbook().createCellStyle();
		// Font headerFont = workbook.createFont();
		// headerFont.setColor(IndexedColors.RED.index);

		headerCellStyle.setFont(font);
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);

		return cellStyle;
	}
	
	
	private XSSFCellStyle getBadRowCellStyle(Sheet completeDataSheet) throws DecoderException {
		// bad red background
		// red color
		//String rgbBadFontColor = "9C0006";
		
		String rgbBadFontColor = "000000";
		byte[] rgbBadFont = Hex.decodeHex(rgbBadFontColor); // get byte array from hex string

		//String rgbBadColor = "F47b91";
		// light pink
		//String rgbBadColor = "F8a5b4";
		
		// orange
		String rgbBadColor = "fbd6a2";
		
		
		byte[] rgbB = Hex.decodeHex(rgbBadColor); // get byte array from hex string
		XSSFColor badColor = new XSSFColor(rgbB, null); // IndexedColorMap has no usage until now. So it can be set
														// null.

		XSSFCellStyle cellStyle = (XSSFCellStyle) completeDataSheet.getWorkbook().createCellStyle();
		cellStyle.setFillForegroundColor(badColor);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Font font = completeDataSheet.getWorkbook().createFont();
		XSSFFont xssfFont = (XSSFFont) font;
		xssfFont.setColor(new XSSFColor(rgbBadFont, null));
		font.setBold(false);

		cellStyle.setFont(font);

		CellStyle headerCellStyle = completeDataSheet.getWorkbook().createCellStyle();
		// Font headerFont = workbook.createFont();
		// headerFont.setColor(IndexedColors.RED.index);

		headerCellStyle.setFont(font);
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);

		return cellStyle;
	}

}
