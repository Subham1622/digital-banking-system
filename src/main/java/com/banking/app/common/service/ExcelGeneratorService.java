package com.banking.app.common.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.banking.app.exception.BankingException;

@Service
public class ExcelGeneratorService implements iExcelGeneratorService {

	@Override
	public ByteArrayInputStream generateExcel(
			String sheetName,
			List<String> headers,
			List<List<String>> data) {

		try (Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			
			Sheet sheet = workbook.createSheet(sheetName);
			
			Row header = sheet.createRow(0);
		
			for(int i=0; i < headers.size(); i++) {
				header.createCell(i).setCellValue(headers.get(i));
			}
			
			int rowNum = 1;
			for(List<String> cellValue : data) {
			
				Row row = sheet.createRow(rowNum++);
				
				IntStream.range(0, cellValue.size())
				.forEach( colIndex -> row.createCell(colIndex).setCellValue(cellValue.get(colIndex)));
			}
			
			IntStream.range(0, headers.size())
			.forEach( i -> sheet.autoSizeColumn(i));
			
			workbook.write(out);
			
			return new ByteArrayInputStream(out.toByteArray());
			
		} catch (Exception e) {
			throw new BankingException("Error generating excel", e);
		}
	}
}
