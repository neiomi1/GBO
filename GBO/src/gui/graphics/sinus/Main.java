package gui.graphics.sinus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        SinusModel sinusModel = new SinusModel();
        SinusPresenter sinusPresenter = new SinusPresenter();
        SinusView sinusView = new SinusView();

        sinusPresenter.setSinusModel(sinusModel);
        sinusPresenter.setSinusView(sinusView);
        sinusView.setSinusPresenter(sinusPresenter);

        sinusPresenter.initView();

        Scene scene = new Scene(sinusPresenter.getView(), 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sinus");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
        // TODO Auto-generated method stub

    }

}
