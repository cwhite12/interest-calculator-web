package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {
    private final Page page;

    private final Locator emailField;
    private final Locator passwordField;
    private final Locator loginButton;

    public LoginPage(Page page) {
        this.page = page;
        this.emailField = page.locator("#UserName");
        this.passwordField = page.locator("#Password");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in"));
    }

    public LoginPage navigate() {
        page.navigate("http://3.8.242.61/Account/Login");
        return this;
    }

    public LoginPage enterEmail(String email) {
        emailField.fill(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.fill(password);
        return this;
    }

    public void submit() {
        loginButton.click();
    }

    public void loginAs(String email, String password) {
        navigate();
        enterEmail(email);
        enterPassword(password);
        submit();

    }
}
