import java.util.*;

// Класс Book
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void markAsLoaned() {
        this.isAvailable = false;
    }

    public void markAsAvailable() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

// Класс Reader
class Reader {
    private int id;
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public Reader(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book, Loan loan) {
        if (book.isAvailable()) {
            book.markAsLoaned();
            borrowedBooks.add(book);
            loan.issueLoan(book, this, new Date());
        } else {
            System.out.println("Book is not available for loan.");
        }
    }

    public void returnBook(Book book, Loan loan) {
        if (borrowedBooks.contains(book)) {
            book.markAsAvailable();
            borrowedBooks.remove(book);
            loan.completeLoan();
        } else {
            System.out.println("Book not found in borrowed list.");
        }
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

// Класс Librarian
class Librarian {
    private int id;
    private String name;
    private String position;

    public Librarian(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public void addBook(Book book, Library library) {
        library.addBook(book);
    }

    public void removeBook(Book book, Library library) {
        library.removeBook(book);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

// Класс Loan
class Loan {
    private Book book;
    private Reader reader;
    private Date loanDate;
    private Date returnDate;

    public void issueLoan(Book book, Reader reader, Date loanDate) {
        this.book = book;
        this.reader = reader;
        this.loanDate = loanDate;
        System.out.println("Loan issued: " + book.getTitle() + " to " + reader.name + " on " + loanDate);
    }

    public void completeLoan() {
        this.returnDate = new Date();
        System.out.println("Loan completed: " + book.getTitle() + " returned on " + returnDate);
    }
}

// Класс Library
class Library {
    private List<Book> books;
    private List<Loan> loans;

    public Library() {
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

// Тестирование
class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian(1, "Alice", "Manager");
        Reader reader = new Reader(1, "John Doe", "john@example.com");

        // Добавление книг
        librarian.addBook(new Book("Book One", "Author A", "12345"), library);
        librarian.addBook(new Book("Book Two", "Author B", "67890"), library);

        System.out.println("\nВсе книги:");
        library.displayBooks();

        // Аренда книги
        Book bookToBorrow = library.findBookByTitle("Book One");
        if (bookToBorrow != null) {
            Loan loan = new Loan();
            reader.borrowBook(bookToBorrow, loan);
        }

        System.out.println("\nДоступные книги:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book);
        }

        // Возврат книги
        Book bookToReturn = library.findBookByTitle("Book One");
        if (bookToReturn != null) {
            Loan loan = new Loan();
            reader.returnBook(bookToReturn, loan);
        }

        System.out.println("\nВсе книги:");
        library.displayBooks();
    }
}


public class Main {
    public static void main(String[] args) {

        }
    }
