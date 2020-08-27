package hu.crs.montebanana.game.components.board;

public enum StepStatus {
    VALID,
    PLAYER_DON_T_HAVE_THAT_CARD,
    PLAYED_SAME_CARD_AS_PREVIOUS_PLAYER_AND_NOT_PLAYERS_LAST_CARD,
    ILLEGAL_LOCATION

}
