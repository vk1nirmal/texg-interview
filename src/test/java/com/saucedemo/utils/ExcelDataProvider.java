package com.saucedemo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataProvider {

    @DataProvider(name = "excelLoginData")
    public static Object[][] readExcelData() throws Exception {
        List<Object[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File("src/test/resources/loginData.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String username = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
            String password = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "";
            boolean shouldLogin = row.getCell(3).getBooleanCellValue();
            String expectedError = row.getCell(4) != null ? row.getCell(4).getStringCellValue() : "";
            data.add(new Object[]{username, password, shouldLogin, expectedError});
        }

        workbook.close();
        fis.close();

        return data.toArray(new Object[0][]);
    }
}
