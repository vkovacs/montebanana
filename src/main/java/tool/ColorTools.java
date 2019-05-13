package tool;

public class ColorTools {
    private ColorTools() {}

    public static String colorText(String text, Color color) {
        return color.getColorCode() + text + Color.RESET.getColorCode();
    }
}
