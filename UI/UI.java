package Library.UI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class UI {
    private final PrintStream out = System.out;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public void menu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Buscar libros por título, autor o categoría.");
        System.out.println("2. Prestar y devolver libros.");
        System.out.println("3. Registrar libros y usuarios.");
        System.out.println("4. Listar libros y usuarios.");
        System.out.println("5. Salir del sistema.");
    }

    public void searchMenu() {
        System.out.println("Seleccione una opción de búsqueda:");
        System.out.println("1. Buscar por título.");
        System.out.println("2. Buscar por autor.");
        System.out.println("3. Buscar por categoría.");
        System.out.println("4. Volver al menú principal.");
    }

    public void borrowReturnMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Prestar un libro.");
        System.out.println("2. Devolver un libro.");
        System.out.println("3. Volver al menú principal.");
    }

    public void registerMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Registrar un nuevo libro.");
        System.out.println("2. Registrar un nuevo usuario.");
        System.out.println("3. Volver al menú principal.");
    }

    public void listMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Mostrar lista de libros.");
        System.out.println("2. Mostrar lista de usuarios.");
        System.out.println("3. Volver al menú principal.");
    }

    public int optionReader() throws Exception {
        return Integer.parseInt(in.readLine());
    }

    public void printText(String mensaje){
        out.println(mensaje);
    }

    public String readText() throws Exception {
        return in.readLine();
    }
}
