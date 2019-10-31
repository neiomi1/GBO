module gbo
{
    requires javafx.controls;

    requires javafx.fxml;

    requires transitive javafx.graphics;

    requires java.scripting;

    requires javafx.base;

    exports gui.animation;

    exports gui.charts;

    exports gui.collections;

    exports gui.container;

    exports gui.controls;

    exports gui.controls2;

    exports gui.dragndrop;

    opens gui.intro;

    exports gui.mvp.contact;

    exports gui.mvp.contact.detail;

    exports gui.mvp.contact.main;

    exports gui.mvp.contact.model;

    exports gui.mvp.contact.overview;

    exports gui.mvp.login;

    exports gui.mvp.login.fxml;

    exports gui.mvp.login.fxml.controller;

    exports gui.mvp.login.fxml.di;

    exports gui.properties;

    exports gui.shapes;

    exports gui.stage;

    exports gui.undoredo;

    exports gui.plusminus;

    opens gui.calculator;

    exports ueb.ueb04.a01;

    exports ueb.ueb04.a02;

    exports ueb.ueb04.a03;

    exports ueb.ueb04.a04;

    opens ueb.ueb04.a05;
}