import org.junit.jupiter.api.Test;
import java.util.List;

public class SI2026Lab2Test {

    @Test
    public void searchBookEveryStatementTest() {
        Library library = new Library();

        boolean exceptionThrown = false;
        try {
            library.searchBookByTitle("");
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assert exceptionThrown;

        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("Clean Code", "Another Author", "Programming"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));

        library.borrowBook("Clean Code", "Another Author");

        List<Book> result = library.searchBookByTitle("Clean Code");
        assert result != null;
        assert result.size() == 1;
        assert result.get(0).getAuthor().equals("Robert C. Martin");

        List<Book> missingResult = library.searchBookByTitle("Harry Potter");
        assert missingResult == null;
    }

    @Test
    public void borrowBookEveryBranchTest() {
        Library library1 = new Library();

        boolean invalidTitleException = false;
        try {
            library1.borrowBook("", "Robert C. Martin");
        } catch (IllegalArgumentException e) {
            invalidTitleException = true;
        }
        assert invalidTitleException;

        Library library2 = new Library();

        boolean invalidAuthorException = false;
        try {
            library2.borrowBook("Clean Code", "");
        } catch (IllegalArgumentException e) {
            invalidAuthorException = true;
        }
        assert invalidAuthorException;

        Library library3 = new Library();
        library3.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));

        library3.borrowBook("Clean Code", "Robert C. Martin");

        List<Book> borrowedSearch = library3.searchBookByTitle("Clean Code");
        assert borrowedSearch == null;

        boolean alreadyBorrowedException = false;
        try {
            library3.borrowBook("Clean Code", "Robert C. Martin");
        } catch (RuntimeException e) {
            alreadyBorrowedException = true;
            assert e.getMessage().equals("Book is already borrowed.");
        }
        assert alreadyBorrowedException;

        Library library4 = new Library();
        library4.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));

        boolean notFoundException = false;
        try {
            library4.borrowBook("1984", "George Orwell");
        } catch (RuntimeException e) {
            notFoundException = true;
            assert e.getMessage().equals("Book not found");
        }
        assert notFoundException;

        Library library5 = new Library();
        library5.addBook(new Book("Clean Code", "Wrong Author", "Programming"));

        boolean wrongAuthorException = false;
        try {
            library5.borrowBook("Clean Code", "Robert C. Martin");
        } catch (RuntimeException e) {
            wrongAuthorException = true;
            assert e.getMessage().equals("Book not found");
        }
        assert wrongAuthorException;
    }

    @Test
    public void searchBookMultipleConditionTest() {
        Library library = new Library();

        Book b1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        Book b2 = new Book("Clean Code", "Another Author", "Programming");
        Book b3 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy");
        Book b4 = new Book("1984", "George Orwell", "Dystopian");

        b2.setBorrowed(true);
        b4.setBorrowed(true);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);

        List<Book> result = library.searchBookByTitle("Clean Code");

        assert result != null;
        assert result.size() == 1;
        assert result.get(0).getAuthor().equals("Robert C. Martin");
    }

    @Test
    public void borrowBookMultipleConditionTest() {
        Library library1 = new Library();

        boolean case1 = false;
        try {
            library1.borrowBook("", "Author");
        } catch (IllegalArgumentException e) {
            case1 = true;
        }
        assert case1;

        Library library2 = new Library();

        boolean case2 = false;
        try {
            library2.borrowBook("Title", "");
        } catch (IllegalArgumentException e) {
            case2 = true;
        }
        assert case2;

        Library library3 = new Library();

        boolean case3 = false;
        try {
            library3.borrowBook("", "");
        } catch (IllegalArgumentException e) {
            case3 = true;
        }
        assert case3;

        Library library4 = new Library();
        library4.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));

        library4.borrowBook("Clean Code", "Robert C. Martin");

        List<Book> result = library4.searchBookByTitle("Clean Code");
        assert result == null;
    }
}
