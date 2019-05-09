import java.text.DecimalFormat;
import java.util.Scanner;

public class IncomeTaxCalculator {

    private static final double TAX_CODE_1819 = 11850;
    private static final double TAX_BASIC_RATE = 0.2;
    private static final double TAX_HIGHER_RATE = 0.4;
    private static final double TAX_ADDITIONAL_RATE = 0.45;
    private static final double BASIC_RATE_UPPER_LIMIT = 34500;
    private static final double HIGHER_RATE_UPPER_LIMIT = 150_000;


    private double calculateBasicRateTaxDue(double annualSalary) {
        return (annualSalary - TAX_CODE_1819) * TAX_BASIC_RATE;
    }

    private double calculateHigherRateTaxDue(double annualSalary) {
        return BASIC_RATE_UPPER_LIMIT * TAX_BASIC_RATE + (annualSalary - (TAX_CODE_1819 + BASIC_RATE_UPPER_LIMIT)) * TAX_HIGHER_RATE;
    }

    private double calculateAdvancedRateTaxDue(double annualSalary) {
        return (BASIC_RATE_UPPER_LIMIT * TAX_BASIC_RATE) + (HIGHER_RATE_UPPER_LIMIT * TAX_HIGHER_RATE)
                + (annualSalary - (TAX_CODE_1819 + BASIC_RATE_UPPER_LIMIT + HIGHER_RATE_UPPER_LIMIT)) * TAX_ADDITIONAL_RATE;
    }

    public double calculatetaxRateToUse(double annualSalary) {
        double taxDue = 0;
        if (annualSalary <= TAX_CODE_1819) {
            taxDue = 0;
        } else if (annualSalary <= BASIC_RATE_UPPER_LIMIT) {
            taxDue = calculateBasicRateTaxDue(annualSalary);
        } else if (annualSalary <= HIGHER_RATE_UPPER_LIMIT) {
            taxDue = calculateHigherRateTaxDue(annualSalary);
        } else {
            taxDue = calculateAdvancedRateTaxDue(annualSalary);
        }
        return taxDue;
    }

    public String calculateTaxOwed(double taxPaid, double taxDue) {
        String taxStatus;
        String taxOwed = new DecimalFormat("##.##").format(Math.abs(taxDue - taxPaid));
        if (taxDue > taxPaid) {
            taxStatus = ("You have not paid enough tax. You owe £" + taxOwed + "! Your total tax due for the year was £" + new DecimalFormat("##.##").format(Math.abs(taxDue)));
        } else if (taxDue < taxPaid) {
            taxStatus = ("You have paid too much tax. You are owed £" + taxOwed + "! Your total tax due for the year was £" + new DecimalFormat("##.##").format(Math.abs(taxDue)));

        } else {
            taxStatus = "You have paid the correct amount of tax, £" + new DecimalFormat("##.##").format(Math.abs(taxDue));
        }
        System.out.println(taxStatus);
        return taxStatus;
    }

    public static void main(String[] args) {
        System.out.println("Have you paid too much income tax? Let's find out! \n" +
                "Do you have your P60?");
        Scanner input = new Scanner(System.in);
        try {

            String p60 = input.next();
            if (p60.equalsIgnoreCase("yes") || p60.equalsIgnoreCase("Y")) {
                System.out.println("For the following questions, please use the information provided on your P60. \n" +
                        "What was your total annual salary last year?");
                double annualSalary = input.nextDouble();
                System.out.println("How much tax did you pay?");
                double taxPaid = input.nextDouble();

                IncomeTaxCalculator incomeTaxCalculator = new IncomeTaxCalculator();
                double taxDue = incomeTaxCalculator.calculatetaxRateToUse(annualSalary);
                incomeTaxCalculator.calculateTaxOwed(taxPaid, taxDue);
            } else {
                System.out.println("Please come back when you have your P60");
            }
        } finally {
            input.close();
        }
    }
}