package com.banking.app.common.service;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface iExcelGeneratorService {

	public ByteArrayInputStream generateExcel(String sheetName,
			List<String> headers,
			List<List<String>> data);
}
