package ueb.ueb12.a02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ueb.ueb12.a02.game.QuizPresenter;
import ueb.ueb12.a02.game.QuizView;
import ueb.ueb12.a02.main.MainPresenter;
import ueb.ueb12.a02.main.MainView;
import ueb.ueb12.a02.model.Model;
import ueb.ueb12.a02.overview.OverviewPresenter;
import ueb.ueb12.a02.overview.OverviewView;

public class Main extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        MainPresenter mainPresenter = new MainPresenter();
        MainView mainView = new MainView();
        QuizPresenter quizPresenter = new QuizPresenter();
        QuizView quizView = new QuizView();
        Model model = ModelInitializer.initModel(this.getParameters().getUnnamed().get(0));
        OverviewView overviewView = new OverviewView();
        OverviewPresenter overviewPresenter = new OverviewPresenter();

        mainPresenter.setModel(model);
        mainPresenter.setOverviewPresenter(overviewPresenter);
        mainPresenter.setQuizPresenter(quizPresenter);
        mainView.setMainPresenter(mainPresenter);

        quizPresenter.setModel(model);
        quizPresenter.setQuizView(quizView);
        quizView.setQuizPresenter(quizPresenter);

        mainPresenter.setMainView(mainView);

        overviewPresenter.setModel(model);
        overviewPresenter.setOverviewView(overviewView);
        overviewView.setOverviewPresenter(overviewPresenter);

        Scene scene = new Scene(mainPresenter.getView(), 600, 300);
        stage.setScene(scene);
        stage.setTitle("Quiz");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
