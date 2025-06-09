package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
/*
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
                    .withFirstName(CommonFunctions.randomString(i * 10))
                    .withLastName(CommonFunctions.randomString(i * 10))
                    .withMiddleName(CommonFunctions.randomString(i * 10)));
        }
*/
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"),
                new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);

        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", " ' ", "", "", "", "", "", "", "", "", "", "", "")));
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
    @MethodSource("contactProvider")
    public void createContactHbm(ContactData contact) {
        List<ContactData> oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.hbm().getContactList();
        List<ContactData> expectedResult = new ArrayList<>(oldContacts);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        ContactData lastElement = newContacts.get(newContacts.size() - 1);
        expectedResult.add(contact.withId(lastElement.id())
                .withFirstName(lastElement.firstName())
                .withLastName(lastElement.lastName())
                .withMiddleName(lastElement.middleName())
                .withAddress(lastElement.address())
                .withEmail(lastElement.email()));
        expectedResult.sort(compareById);
        Assertions.assertEquals(newContacts, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void CanNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

    @Test
    public void testCreateContactWithPhoto() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(5))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

    @Test
    public void testCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(5))
                .withPhoto(randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "1", "2"));
        }
        var group = app.groups().getList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        ContactData lastContact = newRelated.get(newRelated.size() - 1);
        oldRelated.sort(compareById);
        oldRelated.add(contact.withId(lastContact.id())
                .withFirstName(lastContact.firstName())
                .withLastName(lastContact.lastName())
                .withMiddleName(lastContact.middleName())
                .withEmail(lastContact.email())
                .withAddress(lastContact.address())
                .withPhoto(lastContact.photo()));

        newRelated.sort(compareById);
        Assertions.assertEquals(oldRelated, newRelated);
    }

    @Test
    public void testAddExistedContactToGroup() throws InterruptedException {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "1", "2", "3", "", "4", "5", "", "", "", "", "", ""));
        }
        List<ContactData> oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contact = app.contacts().getList().get(index);

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "1", "2"));
        }
        var group = app.groups().getList().get(0);

        app.jdbc().deleteContactFromGroup(contact, group);
        app.contacts().addContactToGroup(contact, group);

        Assertions.assertTrue(app.jdbc().checkLink(group, contact));


    }


}
