package hu.crs.montebanana;

import hu.crs.montebanana.pieces.Player;

import java.util.Scanner;

public class App {
    private int turn = 1;
    Board board = new Board();
    Player player = board.getPlayers().get(0);

    public static void main(String[] args) {
        App app = new App();

        Scanner in = new Scanner(System.in);
        while (app.turn <= 5) {
            System.out.println(app.showStatus());
            System.out.println(app.board.toString());

            String command = in.nextLine();
            int stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
            char stepDirection = Character.toLowerCase(command.split(" ")[1].charAt(0));
            try {
                if (stepDirection == 'r') {
                    app.board.stepRight(app.player, stepCount);
                } else {
                    app.board.stepLeft(app.player, stepCount);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                in.nextLine();
                continue;
            }

            app.turn++;
            System.out.println("\033[H\033[2J");
        }

        System.out.println(app.board.toString());
    }

    private String showStatus() {
        return "Turn: " + turn;
    }
}
