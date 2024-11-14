package test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataReader {

    public static List<Object[]> readBookTestData(String filePath) throws IOException {
        List<Object[]> testData = new ArrayList<>();

        // Open the Excel file
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0); // Assumes data is on the first sheet

        // Iterate over rows (skip the header row)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue; // Skip null rows

            // Retrieve and validate cell data
            String bookId = getCellValueAsString(row.getCell(0));
            String title = getCellValueAsString(row.getCell(1));
            String author = getCellValueAsString(row.getCell(2));
            boolean isAvailable = getCellValueAsBoolean(row.getCell(3));

            // Add data to list
            testData.add(new Object[]{bookId, title, author, isAvailable});
        }

        // Close resources
        workbook.close();
        file.close();

        return testData;
    }

    // Utility method to safely retrieve cell value as a String
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue()); // Adjust based on expected number format
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // Utility method to safely retrieve cell value as a boolean
    private static boolean getCellValueAsBoolean(Cell cell) {
        if (cell == null) return false;
        if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Boolean.parseBoolean(cell.getStringCellValue().trim());
        }
        return false;
    }
}
