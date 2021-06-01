module montebanana.app.main {
    requires static lombok;
    requires spring.boot;
    requires spring.context;
    requires montebanana.core.main;
    requires spring.boot.autoconfigure;
    opens hu.crs.montebanana to spring.core;
    exports hu.crs.montebanana;
}