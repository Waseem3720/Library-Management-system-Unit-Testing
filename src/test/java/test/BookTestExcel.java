package test;

import library.Book;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.ExcelDataReader;

import java.io.IOException;
import java.util.List;

public class BookTestExcel {

    @DataProvider(name = "BookExcel")
    public Object[][] getBookData() throws IOException {
        String filePath = "E:\\Workspace for Eclipse\\Library_Management_System\\src\\test\\resources\\BookExcel.xlsx"; // Ensure correct file extension
        List<Object[]> testData = ExcelDataReader.readBookTestData(filePath);
        return testData.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "BookExcel")
    public void testAddNewBook(String bookId, String title, String author, boolean isAvailable) {
        // Create a Book object
        Book book = new Book(bookId, title, author, isAvailable);

        // Assertions to verify data
        Assert.assertEquals(book.getBookId(), bookId, "Book ID doesn't match");
        Assert.assertEquals(book.getTitle(), title, "Title doesn't match");
        Assert.assertEquals(book.getAuthor(), author, "Author doesn't match");
        Assert.assertEquals(book.isAvailable(), isAvailable, "Availability doesn't match");
    }
}
