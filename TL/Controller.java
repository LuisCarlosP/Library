package Library.TL;

import Library.BL.Entities.*;
import Library.BL.Logic.*;
import Library.Memory.*;
import Library.UI.UI;
import Library.Utils.Utils;

import java.util.List;
import java.util.function.Function;

public class Controller {
    private static LibraryGestor libraryGestor;
    private final UI ui;

    public Controller() {
        libraryGestor= new LibraryGestor(new LibraryDAO());
        this.ui = new UI();
    }

    public void start() throws Exception{
        while (true) {
            ui.menu();
            switch (ui.optionReader()) {
                case 1: searchMenu(); break;
                case 2: borrowReturnMenu(); break;
                case 3: registerMenu(); break;
                case 4: listMenu(); break;
                case 5: return;
                default: ui.printText("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private void registerMenu() throws Exception {
        while (true) {
            ui.registerMenu();
            switch (ui.optionReader()) {
                case 1: registerNewBook(); break;
                case 2: registerNewUser(); break;
                case 3: return;
                default: ui.printText("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private void registerNewUser() throws Exception {
        ui.printText("Ingrese el nombre del nuevo usuario:");
        String name = ui.readText();
        while (Utils.containsLetters(name)) {
            ui.printText("El nombre debe contener al menos una letra y no debe contener números. Ingrese el nombre del nuevo usuario:");
            name = ui.readText();
        }

        ui.printText("Ingrese la identificación del nuevo usuario:");
        String id = ui.readText();
        while (!Utils.containsNumbers(id)) {
            ui.printText("La identificación debe contener al menos un número y no debe contener letras. Ingrese la identificación del nuevo usuario:");
            id = ui.readText();
        }

        libraryGestor.registerUser(new User(name, id));
    }

    private void registerNewBook() throws Exception {
        ui.printText("Ingrese el título del nuevo libro:");
        String title = ui.readText();

        if (libraryGestor.existsBookWithTitle(title)) {
            ui.printText("El libro con el título \"" + title + "\" ya existe en la biblioteca.");
            return;
        }

        ui.printText("Ingrese el autor del nuevo libro:");
        String author = ui.readText();
        while (Utils.containsLetters(author) || Utils.containsNumbers(author)) {
            ui.printText("El autor debe contener al menos una letra y no debe contener números. Ingrese el autor del nuevo libro:");
            author = ui.readText();
        }

        ui.printText("Ingrese la categoría del nuevo libro:");
        String category = ui.readText();

        libraryGestor.addBook(new Book(title, author, category));
    }

    private void listMenu() throws Exception {
        while (true) {
            ui.listMenu();
            switch (ui.optionReader()) {
                case 1:
                    printList(libraryGestor.getBookCollection(), "Lista de libros:",
                            book -> {
                                String availability = book.isAvailable() ? "Disponible" : "No disponible";
                                return "Título: " + book.getTitle() + ", Autor: " + book.getAuthor() + ", Categoría: " + book.getCategory() + ", Estado: " + availability;
                            });
                    break;
                case 2:
                    printList(libraryGestor.getUserRegistry(), "Lista de usuarios:",
                            user -> "Nombre: " + user.getName() + ", Identificación: " + user.getId());
                    break;
                case 3: return;
                default: ui.printText("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private <T> void printList(List<T> list, String header, Function<T, String> formatter) {
        ui.printText(header);
        list.stream()
                .map(formatter)
                .forEach(ui::printText);
    }

    private void searchMenu() throws Exception {
        while (true) {
            ui.searchMenu();
            switch (ui.optionReader()) {
                case 1: searchBookByTitle(); break;
                case 2: searchBookByAuthor(); break;
                case 3: searchBookByCategory(); break;
                case 4: return;
                default: ui.printText("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private void searchBookByTitle() throws Exception {
        ui.printText("Ingrese el título del libro a buscar:");
        Book book = libraryGestor.findBookByTitle(ui.readText());
        if (book != null) {
            String availability = book.isAvailable() ? "Disponible" : "No disponible";
            ui.printText("Libro encontrado: Título: " + book.getTitle() + ", Autor: " + book.getAuthor() + ", Categoría: " + book.getCategory() + ", Estado: " + availability);
        } else {
            ui.printText("No se encontró ningún libro con ese título.");
        }
    }

    private void searchBookByAuthor() throws Exception {
        ui.printText("Ingrese el autor del libro a buscar:");
        List<Book> books = libraryGestor.findBooksByAuthor(ui.readText());
        if (!books.isEmpty()) {
            for (Book book : books) {
                String availability = book.isAvailable() ? "Disponible" : "No disponible";
                ui.printText("Título: " + book.getTitle() + ", Autor: " + book.getAuthor() + ", Categoría: " + book.getCategory() + ", Estado: " + availability);
            }
        } else {
            ui.printText("No se encontraron libros de ese autor.");
        }
    }

    private void searchBookByCategory() throws Exception {
        ui.printText("Ingrese la categoría del libro a buscar:");
        List<Book> books = libraryGestor.findBooksByCategory(ui.readText());
        if (!books.isEmpty()) {
            for (Book book : books) {
                String availability = book.isAvailable() ? "Disponible" : "No disponible";
                ui.printText("Título: " + book.getTitle() + ", Autor: " + book.getAuthor() + ", Categoría: " + book.getCategory() + ", Estado: " + availability);
            }
        } else {
            ui.printText("No se encontraron libros en esa categoría.");
        }
    }

    private void borrowReturnMenu() throws Exception {
        while (true) {
            ui.borrowReturnMenu();
            switch (ui.optionReader()) {
                case 1: borrowBook(); break;
                case 2: returnBook(); break;
                case 3: return;
                default: ui.printText("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private void returnBook() throws Exception {
        ui.printText("Ingrese el título del libro a devolver:");
        String title = ui.readText();
        ui.printText("Ingrese la identificación del usuario:");
        String userId = ui.readText();
        if (libraryGestor.returnBook(title, userId)) {
            ui.printText("Libro devuelto con éxito.");
        } else {
            ui.printText("No se pudo devolver el libro. Verifique que el libro esté prestado y que la identificación del usuario sea correcta.");
        }
    }

    private void borrowBook() throws Exception {
        ui.printText("Ingrese el título del libro a prestar:");
        String title = ui.readText();
        ui.printText("Ingrese la identificación del usuario:");
        String userId = ui.readText();
        if (libraryGestor.borrowBook(title, userId)) {
            ui.printText("Libro prestado con éxito.");
        } else {
            ui.printText("No se pudo prestar el libro. Verifique que el libro esté disponible y que la identificación del usuario sea correcta.");
        }
    }
}