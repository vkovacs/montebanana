package hu.crs.montebanana.game.player.exception;

public class TooManyRegisteredPlayerException extends RuntimeException{
    public TooManyRegisteredPlayerException(String message) {
        super(message);
    }
}
