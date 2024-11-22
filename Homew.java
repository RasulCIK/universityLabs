import java.util.ArrayList;
import java.util.List;

// Класс Книга
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

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rentBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
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

// Класс Читатель
class Reader {
    private String name;
    private List<Book> rentedBooks;

    public Reader(String name) {
        this.name = name;
        this.rentedBooks = new ArrayList<>();
    }

    public boolean rentBook(Book book, Library library) {
        if (book.isAvailable() && rentedBooks.size() < 3) { // Ограничение на 3 книги
            book.rentBook();
            rentedBooks.add(book);
            return true;
        }
        return false;
    }

    public void returnBook(Book book, Library library) {
        if (rentedBooks.contains(book)) {
            book.returnBook();
            rentedBooks.remove(book);
        }
    }

    public List<Book> getRentedBooks() {
        return rentedBooks;
    }
}

// Класс Библиотекарь
class Librarian {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    public void addBook(Book book, Library library) {
        library.getBooks().add(book);
    }

    public void removeBook(Book book, Library library) {
        library.getBooks().remove(book);
    }
}

// Класс Библиотека
class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
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

    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    public void displayAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

// Тестирование системы
class LibraryManagementSystem {
    public static void main(String[] args) {
        // Создаем библиотеку и объекты
        Library library = new Library();
        Librarian librarian = new Librarian("Anna");
        Reader reader = new Reader("John");

        // Добавляем книги
        librarian.addBook(new Book("Book One", "Author A", "ISBN001"), library);
        librarian.addBook(new Book("Book Two", "Author B", "ISBN002"), library);
        librarian.addBook(new Book("Book Three", "Author A", "ISBN003"), library);

        // Отображаем все книги
        System.out.println("All books in the library:");
        library.displayAllBooks();

        // Читатель берет книгу
        Book bookToRent = library.searchByTitle("Book One");
        if (bookToRent != null && reader.rentBook(bookToRent, library)) {
            System.out.println("\nJohn rented 'Book One'.");
        } else {
            System.out.println("\n'Book One' is not available.");
        }

        // Отображаем доступные книги
        System.out.println("\nAvailable books:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book);
        }

        // Читатель возвращает книгу
        reader.returnBook(bookToRent, library);
        System.out.println("\nJohn returned 'Book One'.");

        // Отображаем все книги
        System.out.println("\nAll books in the library:");
        library.displayAllBooks();
    }
}


public class Main {
    public static void main(String[] args) {

    }
}
//searchByTitle для поиска по названию.
//searchByAuthor для поиска по автору.