package pages;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterestCalculatorPage {
    private final Page page;

    private final Locator principalSlider;
    private final Locator rateDropdownButton;
    private final Locator rateDropdownMenu;
    private final Locator dailyItem;
    private final Locator monthlyItem;
    private final Locator yearlyItem;
    private final Locator consentCheckbox;
    private final Locator calculateButton;

    private final Locator interestOutput;
    private final Locator totalOutput;

    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    public InterestCalculatorPage(Page page) {
        this.page = page;

        principalSlider     = page.locator("#customRange1");
        rateDropdownButton  = page.locator("#dropdownMenuButton");
        rateDropdownMenu    = page.locator(".dropdown-menu");

        dailyItem   = page.getByText("Daily",   new Page.GetByTextOptions().setExact(true));
        monthlyItem = page.getByText("Monthly", new Page.GetByTextOptions().setExact(true));
        yearlyItem  = page.getByText("Yearly",  new Page.GetByTextOptions().setExact(true));

        consentCheckbox = page.locator("#gridCheck1");

        calculateButton = page.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName("Calculate"));

        interestOutput = page.locator("#interestAmount");
        totalOutput    = page.locator("#totalAmount");
    }

    public InterestCalculatorPage setPrincipal(int amount) {
        int clamped = Math.max(0, Math.min(15000, amount));
        int finalValue = (clamped / 100) * 100;

        principalSlider.evaluate("(el, value) => {" +
                "  el.value = String(value);" +
                "  el.dispatchEvent(new Event('input', { bubbles: true }));" +
                "  el.dispatchEvent(new Event('change', { bubbles: true }));" +
                "}", finalValue);

        logger.info("Set principal amount to: " + finalValue);
        return this;
    }

    public InterestCalculatorPage chooseRate(String rateLabelText) {
        if (!rateDropdownMenu.isVisible()) {
            rateDropdownButton.click();
        }

        page.getByLabel(rateLabelText, new Page.GetByLabelOptions().setExact(true)).check();

        if (rateDropdownMenu.isVisible()) {
            page.locator("body").click();
            logger.info("Rate dropdown menu still visible, clicked on page to confirm selection.");

        }
        return this;
    }

    public InterestCalculatorPage chooseDuration(String duration) {
        switch (duration.toLowerCase()) {
            case "daily"   -> dailyItem.click();
            case "monthly" -> monthlyItem.click();
            case "yearly"  -> yearlyItem.click();
            default -> throw new IllegalArgumentException("Unknown duration: " + duration);
        }
        return this;
    }

    public InterestCalculatorPage acceptConsent() {
        if (!consentCheckbox.isChecked()) consentCheckbox.check();
        return this;
    }

    public InterestCalculatorPage calculate() {
        calculateButton.click();
        page.waitForTimeout(100); // small but necessary implicit wait
        return this;
    }

    public String interestText() {
        return interestOutput.textContent().trim();
    }

    public String totalText() {
        return totalOutput.textContent().trim();
    }

    //Commas removed, regex finds number in string
    public static double parseMoney(String text) {
        Matcher m = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+").matcher(text.replace(",", ""));
        if (m.find()) return Double.parseDouble(m.group());
        throw new IllegalArgumentException("Could not parse number from: " + text);
    }
}
