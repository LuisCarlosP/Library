package Library.BL.Entities;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> bookCollection;
    private final List<User> userRegistry;

    public Library() {
        this.bookCollection = new ArrayList<>();
        this.userRegistry = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookCollection.add(book);
        System.out.println("El libro \"" + book.getTitle() + "\" ha sido agregado a la biblioteca.");
    }

    public Book findBookByTitle(String title) {
        for (Book book : bookCollection) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    public List<Book> findBooksByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    public void registerUser(User user) {
        userRegistry.add(user);
        System.out.println("El usuario \"" + user.getName() + "\" ha sido registrado.");
    }

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    public List<User> getUserRegistry() {
        return userRegistry;
    }
}