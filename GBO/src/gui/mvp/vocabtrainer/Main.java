package gui.mvp.vocabtrainer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Model m = new Model();
        View v = new View();
        Presenter p = new Presenter();
        p.setModelAndView(m, v);
        v.initView(p);

        Scene scene = new Scene(v.getUI());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vokabel-Training");
        primaryStage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
