package gui.mvp.undoredoquiz;

import gui.mvp.undoredoquiz.editor.EditorPresenter;
import gui.mvp.undoredoquiz.editor.EditorView;
import gui.mvp.undoredoquiz.game.QuizPresenter;
import gui.mvp.undoredoquiz.game.QuizView;
import gui.mvp.undoredoquiz.main.MainPresenter;
import gui.mvp.undoredoquiz.main.MainView;
import gui.mvp.undoredoquiz.model.Model;
import gui.mvp.undoredoquiz.model.Question;
import gui.mvp.undoredoquiz.overview.OverviewPresenter;
import gui.mvp.undoredoquiz.overview.OverviewView;
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

        for (Question q : model.getQuestions())
        {
            System.out.println(q.getIndexOfCorrectAnswer());
        }
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
        editorPresenter.setMainPresenter(mainPresenter);
        editorView.setEditorPresenter(editorPresenter);

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
