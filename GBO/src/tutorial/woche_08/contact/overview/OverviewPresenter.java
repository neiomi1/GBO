package tutorial.woche_08.contact.overview;

import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tutorial.woche_08.contact.detail.DetailPresenter;
import tutorial.woche_08.contact.detail.DetailView;
import tutorial.woche_08.contact.model.Contact;
import tutorial.woche_08.contact.model.ContactModel;

public class OverviewPresenter
{
    private OverviewView view;

    private ContactModel contactModel;

    private Stage detailStage;

    private DetailPresenter detailPresenter;

    public OverviewPresenter()
    {
    }

    public void setView(OverviewView view)
    {
        this.view = view;
    }

    public OverviewView getView()
    {
        return view;
    }

    public void setContactModel(ContactModel contactModel)
    {
        this.contactModel = contactModel;
    }

    public void search()
    {
        String searchPhrase = view.getSearchPhrase();
        final String[] keywords = searchPhrase.split("\\s+");
        List<Contact> hits = contactModel.searchContacts(keywords);
        view.showSearchResults(hits);
    }

    public void contactSelected(Contact contact)
    {
        if (contact == null)
        {
            return;
        }
        if (detailStage == null)
        {
            detailPresenter = new DetailPresenter();
            DetailView detailView = new DetailView();

            detailPresenter.setView(detailView);
            detailPresenter.setContactModel(contactModel);
            detailPresenter.setOverviewPresenter(this);
            detailView.setPresenter(detailPresenter);

            detailStage = new Stage();
            Scene scene = new Scene(detailView);
            detailStage.setScene(scene);
            detailStage.initModality(Modality.APPLICATION_MODAL);
        }
        detailPresenter.setContact(contact);
        detailStage.setTitle("KontaktSystem (" + contact.getLastName() + ")");
        detailStage.show();
    }
}
