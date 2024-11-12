package test;
import library.Book;



import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookTest {

    // Test for getBookId method
    @Test(dataProvider = "bookIdData")
    public void testGetBookId(String expectedBookId, String bookId, String title, String author, boolean isAvailable) {
        Book book = new Book(bookId, title, author, isAvailable);
        assertEquals(book.getBookId(), expectedBookId);
    }

    // Test for getTitle method
    @Test(dataProvider = "bookTitleData")
    public void testGetTitle(String expectedTitle, String bookId, String title, String author, boolean isAvailable) {
        Book book = new Book(bookId, title, author, isAvailable);
        assertEquals(book.getTitle(), expectedTitle);
    }

    // Test for getAuthor method
    @Test(dataProvider = "bookAuthorData")
    public void testGetAuthor(String expectedAuthor, String bookId, String title, String author, boolean isAvailable) {
        Book book = new Book(bookId, title, author, isAvailable);
        assertEquals(book.getAuthor(), expectedAuthor);
    }

    // Test for isAvailable method
    @Test(dataProvider = "bookAvailabilityData")
    public void testIsAvailable(boolean expectedAvailability, String bookId, String title, String author, boolean isAvailable) {
        Book book = new Book(bookId, title, author, isAvailable);
        assertEquals(book.isAvailable(), expectedAvailability);
    }

    // Test for setAvailable method
    @Test(dataProvider = "setAvailableData")
    public void testSetAvailable(boolean expectedAvailability, String bookId, String title, String author, boolean isAvailable) {
        Book book = new Book(bookId, title, author, isAvailable);
        book.setAvailable(expectedAvailability);
        assertEquals(book.isAvailable(), expectedAvailability);
    }

    // DataProvider for getBookId
    @DataProvider
    public Object[][] bookIdData() {
        return new Object[][] {
            {"B001", "B001", "City of Hearts", "Ahmed Faraz", true},
            {"B002", "B002", "Dreams of the World", "Mahmood Khan", false},
            {"B003", "B003", "World of Sufism", "Abdullah Khan", true}
        };
    }

    // DataProvider for getTitle
    @DataProvider
    public Object[][] bookTitleData() {
        return new Object[][] {
            {"City of Hearts", "B001", "City of Hearts", "Ahmed Faraz", true},
            {"Dreams of the World", "B002", "Dreams of the World", "Mahmood Khan", false},
            {"World of Sufism", "B003", "World of Sufism", "Abdullah Khan", true}
        };
    }

    // DataProvider for getAuthor
    @DataProvider
    public Object[][] bookAuthorData() {
        return new Object[][] {
            {"Ahmed Faraz", "B001", "City of Hearts", "Ahmed Faraz", true},
            {"Mahmood Khan", "B002", "Dreams of the World", "Mahmood Khan", false},
            {"Abdullah Khan", "B003", "World of Sufism", "Abdullah Khan", true}
        };
    }

    // DataProvider for isAvailable
    @DataProvider
    public Object[][] bookAvailabilityData() {
        return new Object[][] {
            {true, "B001", "City of Hearts", "Ahmed Faraz", true},
            {false, "B002", "Dreams of the World", "Mahmood Khan", false},
            {true, "B003", "World of Sufism", "Abdullah Khan", true}
        };
    }

    // DataProvider for setAvailable
    @DataProvider
    public Object[][] setAvailableData() {
        return new Object[][] {
            {false, "B001", "City of Hearts", "Ahmed Faraz", true},
            {true, "B002", "Dreams of the World", "Mahmood Khan", false},
            {false, "B003", "World of Sufism", "Abdullah Khan", true}
        };
    }
}
