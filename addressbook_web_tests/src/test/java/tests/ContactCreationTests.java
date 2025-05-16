package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
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

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", " ' ", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        ContactData lastContact = newContacts.get(newContacts.size() - 1);

        expectedList.add(contact.withId(lastContact.id())
                .withFirstName(lastContact.firstName())
                .withLastName(lastContact.lastName())
                .withMiddleName(lastContact.middleName()));

        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void CanNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

}
