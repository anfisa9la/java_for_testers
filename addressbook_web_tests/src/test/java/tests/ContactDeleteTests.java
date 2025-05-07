package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactDeleteTests extends TestBase {


    @Test
    public void testDeleteContact() {
        app.contacts().deleteContact();
    }

}
