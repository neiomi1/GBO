package tutorial.woche_08.contact;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tutorial.woche_08.contact.model.ContactModel;
import tutorial.woche_08.contact.overview.OverviewPresenter;
import tutorial.woche_08.contact.overview.OverviewView;

public class Main extends Application
{
    public void start(Stage stage) throws Exception
    {
        OverviewPresenter overviewPresenter = new OverviewPresenter();
        OverviewView overviewView = new OverviewView();
        ContactModel contactModel = new ContactModel();

        overviewPresenter.setView(overviewView);
        overviewPresenter.setContactModel(contactModel);
        overviewView.setPresenter(overviewPresenter);
        overviewPresenter.search();

        Scene scene = new Scene(overviewPresenter.getView(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Kontaktsystem - Hochschule Trier");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
