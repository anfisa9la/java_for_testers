package ru.stqa.mantis.manager;
import org.openqa.selenium.By;

public class NewUserHelper extends HelperBase {
    public NewUserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void loginNewUser(String user, String email){
        click(By.cssSelector(String.format("a[href^='signup_page.php']")));
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void clickUrl(String url) {
        manager.driver().get(url);
    }

    public void edit(String user, String password){
        click(By.cssSelector("input[placeholder='Real Name']"));
        type(By.name("realname"), user);
        click(By.cssSelector("input[placeholder='Password']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[placeholder='Confirm Password']"));
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

}
