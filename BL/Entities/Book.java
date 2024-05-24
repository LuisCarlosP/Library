package Library.BL.Entities;

public class Book {
    // Attributes
    private String title;
    private String author;
    private String category;
    private boolean available;

    // Constructor
    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public void lendBook() {
        if (available) {
            available = false;
            System.out.println("El libro \"" + title + "\" ha sido prestado.");
        } else {
            System.out.println("Lo siento, el libro \"" + title + "\" no está disponible.");
        }
    }

    public void returnBook() {
        available = true;
        System.out.println("El libro \"" + title + "\" ha sido devuelto.");
    }
}
