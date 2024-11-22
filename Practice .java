//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

// Интерфейс Каталога
interface ICatalog {
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
}

// Интерфейс Учетной системы
interface IAccountingSystem {
    void recordLoan(Book book, Reader reader);
    void recordReturn(Book book, Reader reader);
}

// Класс Book (Книга)
class Book {
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String genre, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
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
                ", genre='" + genre + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

// Класс Reader (Читатель)
class Reader {
    private String name;
    private int ticketNo;

    public Reader(String name, int ticketNo) {
        this.name = name;
        this.ticketNo = ticketNo;
    }

    public String getName() {
        return name;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", ticketNo=" + ticketNo +
                '}';
    }
}

// Класс Catalog (Каталог)
class Catalog implements ICatalog {
    private List<Book> books;

    public Catalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book findByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> findByGenre(String genre) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                results.add(book);
            }
        }
        return results;
    }

    public void displayAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

// Класс AccountingSystem (Учетная система)
class AccountingSystem implements IAccountingSystem {
    private List<String> logs;

    public AccountingSystem() {
        this.logs = new ArrayList<>();
    }

    @Override
    public void recordLoan(Book book, Reader reader) {
        logs.add("Loaned: " + book.getTitle() + " to " + reader.getName());
    }

    @Override
    public void recordReturn(Book book, Reader reader) {
        logs.add("Returned: " + book.getTitle() + " by " + reader.getName());
    }

    public void displayLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

// Класс Librarian (Библиотекарь)
class Librarian {
    private String name;
    private Catalog catalog;
    private AccountingSystem accountingSystem;

    public Librarian(String name, Catalog catalog, AccountingSystem accountingSystem) {
        this.name = name;
        this.catalog = catalog;
        this.accountingSystem = accountingSystem;
    }

    public void issueBook(String title, Reader reader) {
        Book book = catalog.findByTitle(title);
        if (book != null) {
            book.markAsLoaned();
            accountingSystem.recordLoan(book, reader);
            System.out.println("Issued: " + book.getTitle() + " to " + reader.getName());
        } else {
            System.out.println("Book not available or not found.");
        }
    }

    public void returnBook(String title, Reader reader) {
        for (Book book : catalog.findByAuthor(reader.getName())) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.markAsAvailable();
                accountingSystem.recordReturn(book, reader);
                System.out.println("Returned: " + book.getTitle() + " by " + reader.getName());
                return;
            }
        }
        System.out.println("Book not found in the system.");
    }
}

// Основной класс
class LibrarySystem {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        AccountingSystem accountingSystem = new AccountingSystem();
        Librarian librarian = new Librarian("Alice", catalog, accountingSystem);

        // Добавление книг в каталог
        catalog.addBook(new Book("Book One", "Author A", "Fiction", "ISBN001"));
        catalog.addBook(new Book("Book Two", "Author B", "Science", "ISBN002"));
        catalog.addBook(new Book("Book Three", "Author A", "Fiction", "ISBN003"));

        // Создаем читателя
        Reader reader = new Reader("John Doe", 101);

        // Работа библиотекаря
        librarian.issueBook("Book One", reader);
        librarian.returnBook("Book One", reader);

        // Вывод всех книг
        System.out.println("\nAvailable Books:");
        catalog.displayAllBooks();

        // Вывод логов учетной системы
        System.out.println("\nAccounting Logs:");
        accountingSystem.displayLogs();
    }
}
public class Main {
    public static void main(String[] args) {

        }
    }
