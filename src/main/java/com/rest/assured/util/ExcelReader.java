package com.rest.assured.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import com.rest.assured.pojo.Employee;

public class ExcelReader {
	
	public List<Employee> readEmployeesFromExcel(String filePath) throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Skip header row
            }
            String name = row.getCell(1).getStringCellValue();
            String email = row.getCell(2).getStringCellValue();
            String gender = row.getCell(3).getStringCellValue();
            String status = row.getCell(4).getStringCellValue();

            Employee employee = new Employee(name, email, gender, status);
            employeeList.add(employee);
        }

        workbook.close();
        fileInputStream.close();
        return employeeList;
    }

}
