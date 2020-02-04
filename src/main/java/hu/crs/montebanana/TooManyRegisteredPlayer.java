package hu.crs.montebanana;

public class TooManyRegisteredPlayer extends RuntimeException{
    public TooManyRegisteredPlayer(String message) {
        super(message);
    }
}
