package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.InterestCalculatorPage;

import static org.junit.jupiter.api.Assertions.*;
import utils.InterestCalculatorUtils;
public class InterestCalculatorTests extends BaseTest {

    InterestCalculatorUtils interestCalculatorUtils = new InterestCalculatorUtils();

    @Test
    @DisplayName("Yearly 10000 at 5% interest check if calculation is correct")
    void yearlyCalculation() {
        int principal = 10000;
        double rate = 5.0;

        InterestCalculatorPage calc = new InterestCalculatorPage(page)
                .setPrincipal(principal)
                .chooseRate("5%")
                .chooseDuration("yearly")
                .acceptConsent()
                .calculate();

        double expectedI = interestCalculatorUtils.expectedInterest(principal, rate, "yearly");
        double expectedT = principal + expectedI;

        double actualI = InterestCalculatorPage.parseMoney(calc.interestText());
        double actualT = InterestCalculatorPage.parseMoney(calc.totalText());

        assertEquals(interestCalculatorUtils.roundToTwoDecimal(expectedI), interestCalculatorUtils.roundToTwoDecimal(actualI), "Yearly interest mismatch");
        assertEquals(interestCalculatorUtils.roundToTwoDecimal(expectedT), interestCalculatorUtils.roundToTwoDecimal(actualT), "Yearly total mismatch");
    }

    @Test
    @DisplayName("Monthly 2500 at 3% interest check if calculation is correct")
    void monthlyCalculation() {
        int principal = 2500;
        double rate;

        InterestCalculatorPage calc = new InterestCalculatorPage(page)
                .setPrincipal(principal)
                .chooseRate("3%")
                .chooseDuration("monthly")
                .acceptConsent()
                .calculate();

        rate = 3.0;

        double expectedI = interestCalculatorUtils.expectedInterest(principal, rate, "monthly");
        double expectedT = principal + expectedI;

        double actualI = InterestCalculatorPage.parseMoney(calc.interestText());
        double actualT = InterestCalculatorPage.parseMoney(calc.totalText());

        assertEquals(interestCalculatorUtils.roundToTwoDecimal(expectedI), interestCalculatorUtils.roundToTwoDecimal(actualI), "Monthly interest mismatch");
        assertEquals(interestCalculatorUtils.roundToTwoDecimal(expectedT), interestCalculatorUtils.roundToTwoDecimal(actualT), "Monthly total mismatch");
    }

    @Test
    @DisplayName("Daily 1000 at 5% interest check if calculation is correct")
    void dailyCalculation() {
        int principal = 1000;
        double rate = 5.0;

        InterestCalculatorPage calc = new InterestCalculatorPage(page)
                .setPrincipal(principal)
                .chooseRate("5%")
                .chooseDuration("daily")
                .acceptConsent()
                .calculate();

        double expectedInterest = interestCalculatorUtils.expectedInterest(principal, rate, "daily");
        double expectedTotal = principal + expectedInterest;

        double actualInterest = InterestCalculatorPage.parseMoney(calc.interestText());
        double actualTotal = InterestCalculatorPage.parseMoney(calc.totalText());

        assertEquals(interestCalculatorUtils.roundToTwoDecimal(expectedInterest), interestCalculatorUtils.roundToTwoDecimal(actualInterest), "Daily interest mismatch");
        assertEquals(interestCalculatorUtils.roundToTwoDecimal(expectedTotal), interestCalculatorUtils.roundToTwoDecimal(actualTotal), "Daily total mismatch");
    }

    @Test
    @DisplayName("Testing the max rate boundary, 15000 principal yearly with 15% interest rate")
    void maxRateBoundary() {
        int principal = 15000;
        double rate = 15.0;

        InterestCalculatorPage calc = new InterestCalculatorPage(page)
                .setPrincipal(principal)
                .chooseRate("15%")
                .chooseDuration("yearly")
                .acceptConsent()
                .calculate();

        double expectedInterest = interestCalculatorUtils.expectedInterest(principal, rate, "yearly");
        double actualInterest = InterestCalculatorPage.parseMoney(calc.interestText());
        assertEquals(interestCalculatorUtils.roundToTwoDecimal(expectedInterest), interestCalculatorUtils.roundToTwoDecimal(actualInterest), "15% yearly interest mismatch");
    }


}
