package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper {
    private final ApplicationManager manager;

    public ContactHelper(ApplicationManager manager) {
        this.manager = manager;
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
            createContact(new ContactData("name1", "name2", "name3"));
        }
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }


    public boolean isContactPresent() {
        return manager.IsElementPresent(By.name("selected[]"));
    }
}
