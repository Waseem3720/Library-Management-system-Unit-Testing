package test;

import library.Book;
import library.Library;
import library.Member;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryTestExcel {

    @DataProvider(name = "LibraryExcel")
    public Object[][] getLibraryData() throws IOException {
        String filePath = "E:\\Workspace for Eclipse\\Library_Management_System\\src\\test\\resources\\LibraryData.xlsx"; // Adjust the file path if necessary
        List<Object[]> testData = readLibraryTestData(filePath);
        return testData.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "LibraryExcel")
    public void testAddBookAndMember(String bookId, String title, String author, boolean isAvailable, String memberId, String memberName) {
        // Create Library object
        Library library = new Library();

        // Create and add Book
        Book book = new Book(bookId, title, author, isAvailable);
        library.addBook(book);

        // Create and add Member
        Member member = new Member(memberId, memberName);
        library.addMember(member);

        // Assertions to verify the library contains the book and member using custom matching logic
        Assert.assertTrue(isBookInLibrary(library, bookId, title, author, isAvailable), "Library should contain the added book");
        Assert.assertTrue(isMemberInLibrary(library, memberId, memberName), "Library should contain the added member");
    }

    private boolean isBookInLibrary(Library library, String bookId, String title, String author, boolean isAvailable) {
        return library.listBooks().stream().anyMatch(
            b -> b.getBookId().equals(bookId) &&
                 b.getTitle().equals(title) &&
                 b.getAuthor().equals(author) &&
                 b.isAvailable() == isAvailable
        );
    }

    private boolean isMemberInLibrary(Library library, String memberId, String memberName) {
        return library.listMembers().stream().anyMatch(
            m -> m.getMemberId().equals(memberId) &&
                 m.getName().equals(memberName)
        );
    }

    public static List<Object[]> readLibraryTestData(String filePath) throws IOException {
        List<Object[]> testData = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // Assumes data is on the first sheet

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Retrieve cell data
                String bookId = getCellValueAsString(row.getCell(0));
                String title = getCellValueAsString(row.getCell(1));
                String author = getCellValueAsString(row.getCell(2));
                boolean isAvailable = getCellValueAsBoolean(row.getCell(3));
                String memberId = getCellValueAsString(row.getCell(4));
                String memberName = getCellValueAsString(row.getCell(5));

                // Add data to the list
                testData.add(new Object[]{bookId, title, author, isAvailable, memberId, memberName});
            }
        }
        return testData;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

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
