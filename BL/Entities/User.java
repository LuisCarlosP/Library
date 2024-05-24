package Library.BL.Entities;

import java.util.ArrayList;

public class User {
    private String name;
    private String id;
    private final ArrayList<String> booksBorrowed;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.booksBorrowed = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getBooksBorrowed() {
        return booksBorrowed;
    }

    public void borrowBook(String bookName) {
        booksBorrowed.add(bookName);
        System.out.println("El usuario \"" + name + "\" ha tomado prestado el libro: " + bookName);
    }

    public void returnBook(String bookName) {
        if (booksBorrowed.contains(bookName)) {
            booksBorrowed.remove(bookName);
            System.out.println("El usuario \"" + name + "\" ha devuelto el libro: " + bookName);
        } else {
            System.out.println("El usuario \"" + name + "\" no tiene el libro: " + bookName + " para devolver.");
        }
    }

    public void listBorrowedBooks() {
        if (booksBorrowed.isEmpty()) {
            System.out.println("El usuario \"" + name + "\" no tiene libros prestados.");
        } else {
            System.out.println("Libros prestados por el usuario \"" + name + "\":");
            for (String book : booksBorrowed) {
                System.out.println("- " + book);
            }
        }
    }
}