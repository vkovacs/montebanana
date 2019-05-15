package hu.crs.montebanana;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.components.Player;
import tool.Color;

import java.util.Scanner;

import static tool.ColorTools.colorText;

public class App {
    private final Board board = new Board();

    public static void main(String[] args) {
        App app = new App();
        Player actualPlayer = app.board.actualPlayer();

        Scanner in = new Scanner(System.in);
        while (app.board.playersHaveSteps()) {
            System.out.println(app.board.toString());

            String command = in.nextLine();
            int stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
            char stepDirection = Character.toLowerCase(command.split(" ")[1].charAt(0));
            try {
                if (stepDirection == 'r') {
                    app.board.stepRight(actualPlayer, stepCount);
                } else {
                    app.board.stepLeft(actualPlayer, stepCount);
                }
            } catch (Exception e) {
                System.out.println(colorText(e.getMessage(), Color.RED));
                System.out.println();
                in.nextLine();
                continue;
            }

            colorText("", Color.RESET);
            actualPlayer = app.board.nextPlayer();
        }

        System.out.println(app.board.toString());

        System.out.println(app.board.winnerToString());
    }

}
