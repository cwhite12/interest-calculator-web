package utils;

public class InterestCalculatorUtils {

    public double expectedInterest(double principalAmount, double interestRatePct, String durationType) {
        double yearlyRate = interestRatePct / 100.0;
        return switch (durationType.toLowerCase()) {
            case "daily" -> principalAmount * yearlyRate * (1.0 / 365.0);
            case "monthly" -> principalAmount * yearlyRate * (1.0 / 12.0);
            case "yearly" -> principalAmount * yearlyRate * 1.0;
            default -> throw new IllegalArgumentException("Unknown duration: " + durationType);
        };
    }


    public double roundToTwoDecimal(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}

