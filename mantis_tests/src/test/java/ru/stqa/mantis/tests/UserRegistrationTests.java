package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.io.IOException;
import java.time.Duration;
import java.util.regex.Pattern;
import okhttp3.OkHttpClient;

public class UserRegistrationTests extends TestBase {

    @Test
    void testRegistration() throws IOException, InterruptedException {
        var username = String.format("%s", CommonFunctions.randomString(5));
        var email = String.format("%s@localhost", username);
        var password = String.format("%s", CommonFunctions.randomString(10));

        app.jamesCli().addUser(email, password);
        app.newUser().loginNewUser(username, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(180));
        System.out.println(messages);

        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(messages.get(0).content());
        if (matcher.find()) {
            var url = messages.get(0).content().substring(matcher.start(), matcher.end());
            System.out.println(url);

            app.newUser().clickUrl(url);
            app.newUser().edit(username, password);

            app.http().login(username, password);
            Assertions.assertTrue(app.http().isLoggedIn());
        } else {
            throw new RuntimeException("no link found");
        }
    }

    @Test
    void testAPIRegistration() throws IOException, InterruptedException {
        var username = String.format("%s", CommonFunctions.randomString(5));
        var email = String.format("%s@localhost", username);
        var password = String.format("%s", CommonFunctions.randomString(10));

        app.JamesAPI().addUser(email, password);
        app.newUser().loginNewUser(username, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(180));
        System.out.println(messages);

        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(messages.get(0).content());
        if (matcher.find()) {
            var url = messages.get(0).content().substring(matcher.start(), matcher.end());
            System.out.println(url);

            app.newUser().clickUrl(url);
            app.newUser().edit(username, password);

            app.http().login(username, password);
            Assertions.assertTrue(app.http().isLoggedIn());
        } else {
            throw new RuntimeException("no link found");
        }
    }
}
