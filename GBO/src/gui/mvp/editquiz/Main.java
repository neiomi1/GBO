package gui.mvp.editquiz;

import gui.mvp.editquiz.editor.EditorPresenter;
import gui.mvp.editquiz.editor.EditorView;
import gui.mvp.editquiz.game.QuizPresenter;
import gui.mvp.editquiz.game.QuizView;
import gui.mvp.editquiz.main.MainPresenter;
import gui.mvp.editquiz.main.MainView;
import gui.mvp.editquiz.model.Model;
import gui.mvp.editquiz.overview.OverviewPresenter;
import gui.mvp.editquiz.overview.OverviewView;
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
        EditorPresenter editorPresenter = new EditorPresenter();
        EditorView editorView = new EditorView();

        mainPresenter.setModel(model);
        mainPresenter.setOverviewPresenter(overviewPresenter);
        mainPresenter.setQuizPresenter(quizPresenter);
        mainPresenter.setEditorPresenter(editorPresenter);
        mainView.setMainPresenter(mainPresenter);

        quizPresenter.setModel(model);
        quizPresenter.setQuizView(quizView);
        quizView.setQuizPresenter(quizPresenter);

        mainPresenter.setMainView(mainView);

        overviewPresenter.setModel(model);
        overviewPresenter.setOverviewView(overviewView);
        overviewView.setOverviewPresenter(overviewPresenter);

        editorPresenter.setEditorView(editorView);
        editorPresenter.setModel(model);
        editorView.setEditorPresenter(editorPresenter);

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
