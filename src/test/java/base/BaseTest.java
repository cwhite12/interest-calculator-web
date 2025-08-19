package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import tests.LoginTests;

import java.util.logging.Logger;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String baseUrl = System.getProperty("baseUrl", "http://3.8.242.61/ ");
    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());
    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        logger.info("Playwright session started.");
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        logger.info("Chromium browser launched.");
    }

    @AfterAll
    static void tearDownAll() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate(baseUrl);
        logger.info("Browser and page context initialised.");
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginAs("conorwhite123@icloud.com","Ten123!");
        logger.info("Logged in as conorwhite123@icloud.com");
    }

    @AfterEach
    void cleanup() {
        if (context != null) context.close();
    }
}