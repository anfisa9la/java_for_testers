package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactDeleteTests extends TestBase {

    @Test
    public void deleteContactHbm() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "1", "2", "3", "", "4", "5"));
        }
        List<ContactData> oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContactWithId(oldContacts.get(index));
        List<ContactData> newContacts = app.hbm().getContactList();
        List<ContactData> expectedResult = new ArrayList<>(oldContacts);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedResult.remove(index);
        expectedResult.sort(compareById);

        Assertions.assertEquals(newContacts, expectedResult);
    }

    @Test
    public void testDeleteContact() {
        app.contacts().deleteContact();
    }

    @Test
    public void testDeleteAllContacts() {
        app.contacts().removeAllContacts();
    }

}
