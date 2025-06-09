package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Name", "middleName", "lastName", "", "address", "email", "1111", "", "123321", "", "email2", "email3"));
            app.contacts().returnToHomePage();
        }

        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && "".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getAllPhones();
        Assertions.assertEquals(expected, phones);


    }

    @Test
    void testAllContactInfo() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Name", "middleName", "lastName", "", "address", "email", "1234", "4567", "111", "888", "testemail2", "testemail3"));
            app.contacts().returnToHomePage();
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var address = app.contacts().getAddress(contact);
        var emails = app.contacts().getEmails(contact);
        var expectedPhones = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedAddress = Stream.of(contact.address())
                .collect(Collectors.joining());
        var expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPhones, phones);
        Assertions.assertEquals(expectedAddress, address);
        Assertions.assertEquals(expectedEmails, emails);


    }
}
