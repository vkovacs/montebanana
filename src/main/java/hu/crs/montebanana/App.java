package hu.crs.montebanana;

import hu.crs.montebanana.pieces.Player;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Board board = new Board();
        Player player = board.getPlayers().get(0);

        Scanner in = new Scanner(System.in);
        while (board.getTurn() <= 5) {
            System.out.println(board.toString());

            String command = in.nextLine();
            int stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
            char stepDirection = Character.toLowerCase(command.split(" ")[1].charAt(0));
            try {
                if (stepDirection == 'r') {
                    board.stepRight(player, stepCount);
                } else {
                    board.stepLeft(player, stepCount);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                in.nextLine();
                continue;
            }

            board.nextTurn();
            System.out.println("\033[H\033[2J");
        }

        System.out.println(board.toString());
    }
}
