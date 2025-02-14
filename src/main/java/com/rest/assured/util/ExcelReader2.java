package com.rest.assured.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import com.rest.assured.pojo.Bicycle;
import com.rest.assured.pojo.Book;


public class ExcelReader2 {
	
	public Map<String, Object> readStoreDataFromExcel(String filePath) throws IOException {
        
		List<Book> bookList = new ArrayList<>();
        Bicycle bicycle = new Bicycle();
        
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Skip header row
            }
            String category = row.getCell(1).getStringCellValue();
            String author = row.getCell(2).getStringCellValue();
            String title = row.getCell(3).getStringCellValue();
            int bookPrice = Integer.valueOf(row.getCell(4).getStringCellValue());

            Book book = new Book(category, author, title, bookPrice);
            bookList.add(book);
        }
        
        Row bicycleRow = sheet.getRow(5);
        bicycle.setColor(bicycleRow.getCell(5).getStringCellValue());
        bicycle.setBicyclePrice(bicycleRow.getCell(6).getNumericCellValue());

        workbook.close();
        fileInputStream.close();
        
        Map<String, Object> data = new HashMap<>();
        data.put("books", bookList);
        data.put("bicycle", bicycle);
        
        return data;
    }
	
}
