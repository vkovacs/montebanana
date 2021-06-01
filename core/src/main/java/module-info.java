module montebanana.core.main {
    requires static lombok;
    requires spring.context;
    exports hu.crs.montebanana.game;
    exports hu.crs.montebanana.game.components.config;
    exports hu.crs.montebanana.game.player;
    exports hu.crs.montebanana.game.components;
    opens hu.crs.montebanana.game.components.config;

}