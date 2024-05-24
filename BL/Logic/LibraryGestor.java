package Library.BL.Logic;

import Library.Memory.LibraryDAO;
import Library.BL.Entities.Book;
import Library.BL.Entities.User;
import java.util.List;

public class LibraryGestor {
    private final LibraryDAO libraryDAO;

    public LibraryGestor(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    public void addBook(Book book) {
        libraryDAO.addBook(book);
    }

    public Book findBookByTitle(String title) {
        return libraryDAO.findBookByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        return libraryDAO.findBooksByAuthor(author);
    }

    public List<Book> findBooksByCategory(String category) {
        return libraryDAO.findBooksByCategory(category);
    }

    public void registerUser(User user) {
        libraryDAO.registerUser(user);
    }

    public List<Book> getBookCollection() {
        return libraryDAO.getBookCollection();
    }

    public List<User> getUserRegistry() {
        return libraryDAO.getUserRegistry();
    }

    public boolean borrowBook(String title, String userId) {
        Book book = libraryDAO.findBookByTitle(title);
        User user = libraryDAO.findUserById(userId);
        if (book != null && user != null && book.isAvailable()) {
            book.lendBook();
            user.borrowBook(title);
            return true;
        }
        return false;
    }

    public boolean returnBook(String title, String userId) {
        Book book = libraryDAO.findBookByTitle(title);
        User user = libraryDAO.findUserById(userId);
        if (book != null && user != null && !book.isAvailable() && user.getBooksBorrowed().contains(title)) {
            book.returnBook();
            user.returnBook(title);
            return true;
        }
        return false;
    }

    public boolean existsBookWithTitle(String title) {
        return libraryDAO.existsBookWithTitle(title);
    }
}