package tutorial.woche_08.contact.overview;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import tutorial.woche_08.contact.model.Contact;

public class OverviewView extends VBox
{
    private OverviewPresenter presenter;

    private TextField searchField;

    private ListView<Contact> resultsList;

    public OverviewView()
    {
        initView();
    }

    private void initView()
    {
        setSpacing(10);
        HBox searchBar = new HBox(10);

        searchBar.getChildren().add(new Label("Suchen:"));
        searchField = new TextField();
        searchField.setPrefColumnCount(20);
        searchField.setOnAction(e -> presenter.search());
        searchBar.getChildren().add(searchField);

        Button searchButton = new Button("Suchen");
        searchButton.setOnAction(e -> presenter.search());
        searchBar.getChildren().add(searchButton);

        getChildren().add(searchBar);

        resultsList = new ListView<Contact>();
        resultsList.getSelectionModel().selectedItemProperty().addListener

        ((obs, oldValue, newValue) -> presenter.contactSelected(newValue));
        BorderPane.setMargin(resultsList, new Insets(10, 10, 10, 10));
        VBox.setVgrow(resultsList, Priority.ALWAYS);
        getChildren().add(resultsList);
    }

    public void setPresenter(OverviewPresenter presenter)
    {
        this.presenter = presenter;
    }

    public String getSearchPhrase()
    {
        return searchField.getText();
    }

    public void showSearchResults(List<Contact> searchResults)
    {
        resultsList.getItems().setAll(searchResults);
    }
}
