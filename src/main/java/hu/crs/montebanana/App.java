package hu.crs.montebanana;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.fill;

public class App {
    public static void main(String[] args) throws InterruptedException {
        char[] mountain = new char[13];

        while (true) {
            int monkeyLocation = ThreadLocalRandom.current().nextInt(13);
            mountain[monkeyLocation] = '@';

            System.out.println(toString(mountain));
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("\033[H\033[2J");

            fill(mountain, ' ');
        }

    }

    private static String toString(char[] array) {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (char c : array) {
            if (c == ' ') stringBuilder.append("  ");
            else stringBuilder.append("@ ");
        }
        return stringBuilder.toString();
    }

}
