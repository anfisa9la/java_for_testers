package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();

        for (var name : List.of("", "first_name")) {
            for (var last_name : List.of("", "last_name")) {
                for (var middle_name : List.of("", "middle_name")) {
                    result.add(new ContactData()
                            .withFirstName(name)
                            .withLastName(last_name)
                            .withMiddleName(middle_name));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withMiddleName(randomString(i * 10)));
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testCreateContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

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
