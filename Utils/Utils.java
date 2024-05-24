package Library.Utils;

public class Utils {
    public static boolean containsNumbers(String input) {
        return input.matches(".*\\d.*") && !input.matches(".*[a-zA-Z].*");
    }

    public static boolean containsLetters(String input) {
        return !input.matches(".*[a-zA-Z].*") || input.matches(".*\\d.*");
    }
}