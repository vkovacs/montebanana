package hu.crs.montebanana.player;

public class NoSuchPlayerException extends RuntimeException {
    public NoSuchPlayerException(String message) {
        super(message);
    }
}
