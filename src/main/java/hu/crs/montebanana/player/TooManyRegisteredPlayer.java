package hu.crs.montebanana.player;

public class TooManyRegisteredPlayer extends RuntimeException{
    public TooManyRegisteredPlayer(String message) {
        super(message);
    }
}
