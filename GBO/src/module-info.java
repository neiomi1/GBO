// Calc braucht opens
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

    exports gui.mvp.vocabtrainer;

    exports gui.mvp.basicquiz;

    exports gui.properties;

    exports gui.shapes;

    exports gui.stage;

    exports gui.undoredo;

    exports gui.plusminus;

    opens gui.calculator;

    exports gui.pizza;

    exports gui.mvp.basicquiztable;

    opens gui.mvp.basicquiztable.model;

    exports ueb.ueb04.a01;

    exports ueb.ueb04.a02;

    exports ueb.ueb04.a03;

    exports ueb.ueb04.a04;

    opens ueb.ueb04.a05;

    exports ueb.ueb05.a01;

    exports ueb.ueb06.a01;

    exports ueb.ueb06.a02;

    exports ueb.ueb06.a03;

    exports ueb.ueb06.a04;

    exports gui.country.combo;

    exports tutorial.woche_06;

    exports tutorial.woche_07;

    exports tutorial.woche_08.contact;

    exports tutorial.woche_08.bindingTest;

    exports ueb.ueb07.a02;

}