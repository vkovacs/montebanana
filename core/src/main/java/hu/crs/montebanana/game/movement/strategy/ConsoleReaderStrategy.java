package hu.crs.montebanana.game.movement.strategy;

import hu.crs.montebanana.game.ExitGameException;
import hu.crs.montebanana.game.MonteBanana;
import hu.crs.montebanana.game.components.board.Board;
import hu.crs.montebanana.game.components.board.StepStatus;
import hu.crs.montebanana.game.movement.Direction;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.rendering.Label;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class ConsoleReaderStrategy implements MovementStrategy {
    public Movement next(Board board, Player player) {
        Scanner in = new Scanner(System.in);
        return readMovement(in, board, player);
    }

    private Movement readMovement(Scanner in, Board board, Player player) {
        int stepCount;
        Direction stepDirection;
        while (true) {
            try {
                String command = in.nextLine();
                stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
                stepDirection = Direction.valueOfChar(Character.toLowerCase(command.split(" ")[1].charAt(0)));


                Movement movement = new Movement(stepCount, stepDirection);
                StepStatus stepStatus = board.isValidMovement(player, movement);
                switch (stepStatus) {
                    case VALID : return movement;
                    case PLAYER_DON_T_HAVE_THAT_CARD: System.out.println(error("Player don't have that card!")); break;
                    case PLAYED_SAME_CARD_AS_PREVIOUS_PLAYER_AND_NOT_PLAYERS_LAST_CARD: System.out.println(error("Cannot play the same card as previous player!")); break;
                    case ILLEGAL_LOCATION: System.out.println(error("Illegal location!")); break;
                }

            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println(error("Incorrect input! Try again!"));
            } catch (NoSuchElementException e) {
                throw new ExitGameException();
            }
        }
    }

    private String error(String message) {
        return new Label(message, Color.RED_BOLD).accept(MonteBanana.RENDERER_VISITOR);
    }
}
