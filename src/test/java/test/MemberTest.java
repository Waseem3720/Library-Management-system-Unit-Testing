package test;
import library.Book;
import library.Member;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MemberTest {

    // Test for borrowBook method
    @Test(dataProvider = "borrowBookData")
    public void testBorrowBook(String expectedMessage, boolean isAvailable, String bookId, String title, String author, String memberId, String memberName) {
        Book book = new Book(bookId, title, author, isAvailable);
        Member member = new Member(memberId, memberName);
        assertEquals(member.borrowBook(book), expectedMessage);
    }

    // Test for returnBook method
    @Test(dataProvider = "returnBookData")
    public void testReturnBook(String expectedMessage, boolean isAvailable, String bookId, String title, String author, String memberId, String memberName) {
        Book book = new Book(bookId, title, author, isAvailable);
        Member member = new Member(memberId, memberName);
        assertEquals(member.returnBook(book), expectedMessage);
    }

    // Test for getMemberId method
    @Test(dataProvider = "memberIdData")
    public void testGetMemberId(String expectedMemberId, String memberId, String memberName) {
        Member member = new Member(memberId, memberName);
        assertEquals(member.getMemberId(), expectedMemberId);
    }

    // Test for getName method
    @Test(dataProvider = "memberNameData")
    public void testGetName(String expectedMemberName, String memberId, String memberName) {
        Member member = new Member(memberId, memberName);
        assertEquals(member.getName(), expectedMemberName);
    }

    // Test for getDetails method
    @Test(dataProvider = "memberDetailsData")
    public void testGetDetails(String expectedDetails, String memberId, String memberName) {
        Member member = new Member(memberId, memberName);
        assertEquals(member.getDetails(), expectedDetails);
    }

    // DataProvider for borrowBook
    @DataProvider
    public Object[][] borrowBookData() {
        return new Object[][] {
            {"Book borrowed: City of Hearts", true, "B001", "City of Hearts", "Ahmed Faraz", "M001", "Ali Ahmed"},
            {"Book is currently unavailable.", false, "B002", "Dreams of the World", "Mahmood Khan", "M002", "Sara Ali"},
            {"Book borrowed: World of Sufism", true, "B003", "World of Sufism", "Abdullah Khan", "M003", "Aslam Raza"}
        };
    }

    // DataProvider for returnBook
    @DataProvider
    public Object[][] returnBookData() {
        return new Object[][] {
            {"Book returned: City of Hearts", false, "B001", "City of Hearts", "Ahmed Faraz", "M001", "Ali Ahmed"},
            {"Book returned: Dreams of the World", true, "B002", "Dreams of the World", "Mahmood Khan", "M002", "Sara Ali"},
            {"Book returned: World of Sufism", true, "B003", "World of Sufism", "Abdullah Khan", "M003", "Aslam Raza"}
        };
    }

    // DataProvider for getMemberId
    @DataProvider
    public Object[][] memberIdData() {
        return new Object[][] {
            {"M001", "M001", "Ali Ahmed"},
            {"M002", "M002", "Sara Ali"},
            {"M003", "M003", "Aslam Raza"}
        };
    }

    // DataProvider for getName
    @DataProvider
    public Object[][] memberNameData() {
        return new Object[][] {
            {"Ali Ahmed", "M001", "Ali Ahmed"},
            {"Sara Ali", "M002", "Sara Ali"},
            {"Aslam Raza", "M003", "Aslam Raza"}
        };
    }

    // DataProvider for getDetails
    @DataProvider
    public Object[][] memberDetailsData() {
        return new Object[][] {
            {"Member ID: M001, Name: Ali Ahmed", "M001", "Ali Ahmed"},
            {"Member ID: M002, Name: Sara Ali", "M002", "Sara Ali"},
            {"Member ID: M003, Name: Aslam Raza", "M003", "Aslam Raza"}
        };
    }
}
