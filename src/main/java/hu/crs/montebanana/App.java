package hu.crs.montebanana;

import java.util.Scanner;

public class App {

    //TODO: terminal raw mode https://www.darkcoding.net/software/non-blocking-console-io-is-not-possible/
    public static void main(String[] args) throws InterruptedException {
        char[] mountain = new char[13];

        int monkeyIndex = 1;
        Scanner in = new Scanner(System.in);
        while (true) {
            mountain[monkeyIndex] = '@';
            System.out.println(toString(mountain));
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println(arrow(monkeyIndex));
            String next = in.next();

            mountain[monkeyIndex] = ' ';
            if ("d".equals(next)) {
                if (monkeyIndex < 12) monkeyIndex++;
            } else if ("a".equals(next)) {
                if (monkeyIndex > 0) monkeyIndex--;
            }
            System.out.println("\033[H\033[2J");
        }

    }

    private static String arrow(int monkeyIndex) {
        if (monkeyIndex == 0) {
            return "^";
        } else {
            return "-".repeat((monkeyIndex * 2) - 1) +"/^";

        }
    }

    private static String toString(char[] array) {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (char c : array) {
            if (c == '@') stringBuilder.append("@ ");
            else stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }

}
