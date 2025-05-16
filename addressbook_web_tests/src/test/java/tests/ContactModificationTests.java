package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() throws InterruptedException {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("", "0", "1", "2"));
        }

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("new name").withLastName("new last name");

        app.contacts().modifyContact(oldContacts.get(index), testData);
        Thread.sleep(2000);

        var newContacts = app.contacts().getList();
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        expectedList.sort((compareById));
        Assertions.assertEquals(expectedList, newContacts);
    }
}