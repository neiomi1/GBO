package tutorial.woche_08.contact.detail;

import tutorial.woche_08.contact.model.Contact;
import tutorial.woche_08.contact.model.ContactModel;
import tutorial.woche_08.contact.overview.OverviewPresenter;

public class DetailPresenter
{
    private DetailView view;

    private OverviewPresenter presenter;

    private ContactModel contactModel;

    public DetailPresenter()
    {
    }

    public void setView(DetailView view)
    {
        this.view = view;
    }

    public DetailView getView()
    {
        return view;
    }

    public void setOverviewPresenter(OverviewPresenter overviewPresenter)
    {
        this.presenter = overviewPresenter;
    }

    public void setContactModel(ContactModel contactModel)
    {
        this.contactModel = contactModel;
    }

    public void setContact(Contact contact)
    {
        view.showContact(contact);
    }

    public void save()
    {
        Contact updatedContact = view.getContact();
        contactModel.updateContact(updatedContact);
        presenter.search();
    }

    public void cancel()
    {
        close();
    }

    private void close()
    {
        view.getScene().getWindow().hide();
    }
}
