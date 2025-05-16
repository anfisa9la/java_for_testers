package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void createContact(ContactData contact) {
        manager.driver.findElement(By.linkText("add new")).click();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstName());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middleName());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastName());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }


    public void deleteContact() {
        if (!isContactPresent()) {
            createContact(new ContactData("id", "name1", "name2", "name3"));
        }
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    public boolean isContactPresent() {
        return manager.IsElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        selectAllContacts();
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectAllContacts() {
        click(By.id("MassCB"));
    }


    public void returnToHomePage() {
        click(By.linkText("home"));
    }


    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs){
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getDomAttribute("value");
            var lastname = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstname = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var middlename = tr.findElement(By.cssSelector("td:nth-child(4)")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withMiddleName(middlename));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        selectContact(contact);
        initModifyContact();
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    public void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    private void initModifyContact() {
        click(By.xpath("//img[@alt=\'Edit\']"));
    }

    private void fillContactForm(ContactData contact) {
        click(By.name("firstname"));
        type(By.name("firstname"), contact.firstName());
        click(By.name("lastname"));
        type(By.name("lastname"), contact.lastName());
        click(By.name("middlename"));
        type(By.name("middlename"), contact.middleName());
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

}
