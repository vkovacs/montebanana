package tool;

public class ColorTools {
    public static String colorText(String text, Color color) {
        return color.getColorCode() + text + Color.RESET.getColorCode();
    }
}
