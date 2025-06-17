package ru.stqa.mantis.manager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private NewUserHelper newUserHelper;
    private JamesAPIHelper jamesAPIHelper;
    private RestApiHelper RestApiHelper;
    private SoapApiHelper soapApiHelper;


    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

    }

    public WebDriver driver() {
        if (driver == null) {

            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(917, 696));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public NewUserHelper newUser() {
        if (newUserHelper == null) {
            newUserHelper = new NewUserHelper(this);
        }
        return newUserHelper;
    }

    public JamesAPIHelper JamesAPI() {
        if (jamesAPIHelper == null) {
            jamesAPIHelper = new JamesAPIHelper(this);
        }
        return jamesAPIHelper;
    }

    public RestApiHelper rest() {
        if (RestApiHelper == null) {
            RestApiHelper = new RestApiHelper(this);
        }
        return RestApiHelper;
    }

    public SoapApiHelper soap() {
        if (soapApiHelper == null) {
            soapApiHelper = new SoapApiHelper(this);
        }
        return soapApiHelper;
    }
}
