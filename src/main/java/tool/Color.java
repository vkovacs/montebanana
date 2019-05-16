package tool;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Color {
    RED("\u001B[31m"), GREEN("\u001B[32m"), BLUE("\u001B[34m"), YELLOW("\u001B[33m"), RESET("\u001B[0m"),
    RED_BOLD("\033[1;31m");

    @Getter
    private String colorCode;
}
