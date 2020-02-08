package hu.crs.montebanana.player.exception;

public class TooManyRegisteredPlayerException extends RuntimeException{
    public TooManyRegisteredPlayerException(String message) {
        super(message);
    }
}
