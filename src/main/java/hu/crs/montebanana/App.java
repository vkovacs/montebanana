package hu.crs.montebanana;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App {

    //TODO: terminal raw mode https://www.darkcoding.net/software/non-blocking-console-io-is-not-possible/
    public static void main(String[] args) {
        int turn = 1;
        Set<Integer> availableCards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
        char[] mountain = new char[13];

        int monkeyIndex = -1;
        int newMonkeyIndex = -1;
        Scanner in = new Scanner(System.in);
        while (availableCards.size() > 0) {
            System.out.println("Turn: " + turn);

            if (monkeyIndex >= 0) mountain[monkeyIndex] = '@';
            System.out.println(toString(mountain));
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");

            String command = in.nextLine();
            System.out.println(Arrays.toString(command.split(" ")));
            int stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
            char stepDirection = Character.toLowerCase(command.split(" ")[1].charAt(0));
            if (monkeyIndex >= 0) mountain[monkeyIndex] = ' ';

            newMonkeyIndex = (stepDirection == 'r' ? monkeyIndex + stepCount : monkeyIndex - stepCount);
            if (newMonkeyIndex < 0 || newMonkeyIndex > 12) continue;

            monkeyIndex = newMonkeyIndex;
            turn++;
            System.out.println("\033[H\033[2J");
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
