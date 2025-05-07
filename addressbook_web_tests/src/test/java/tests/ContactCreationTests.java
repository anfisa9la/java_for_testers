package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testCreateContact() {
        app.contacts().createContact(new ContactData("1111", "2222", "3333"));
    }

    @Test
    public void testCreateContactWithFirstNameOnly() {
        app.contacts().createContact(new ContactData().withFirstName("1111"));
    }

    @Test
    public void testCreateContactWithEmptyFields() {
        app.contacts().createContact(new ContactData());
    }
}
