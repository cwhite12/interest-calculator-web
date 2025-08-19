package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.LoginPage;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class LoginTests extends BaseTest {

    @Test
   public void canLoginSuccessfully() {
        LoginPage login = new LoginPage(page);
        login.loginAs("conorwhite123@icloud.com", "Ten123!");

        assertThat(page.getByText("Hello conorwhite123@icloud.com")).isVisible();
    }
}
