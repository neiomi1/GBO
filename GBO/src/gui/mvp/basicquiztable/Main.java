package gui.mvp.basicquiztable;

import gui.mvp.basicquiztable.game.QuizPresenter;
import gui.mvp.basicquiztable.game.QuizView;
import gui.mvp.basicquiztable.main.MainPresenter;
import gui.mvp.basicquiztable.main.MainView;
import gui.mvp.basicquiztable.model.Model;
import gui.mvp.basicquiztable.overview.OverviewPresenter;
import gui.mvp.basicquiztable.overview.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        Scene scene = new Scene(mainPresenter.getView(), 400, 200);
        stage.setScene(scene);
        stage.setTitle("Quiz");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
