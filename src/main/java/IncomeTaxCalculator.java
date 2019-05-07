import java.text.DecimalFormat;
import java.util.Scanner;

public class IncomeTaxCalculator {

    private static final double TAX_CODE_1819 = 11850;
    private static final double TAX_BASIC_RATE = 0.2;
    private static final double TAX_HIGHER_RATE = 0.4;
    private static final double TAX_ADDITIONAL_RATE = 0.45;
    private static final double BASIC_RATE_UPPER_LIMIT = 34500;
    private static final double HIGHER_RATE_UPPER_LIMIT = 150_000;
    private double taxDue;

    private void calculateBasicRate(double annualSalary) {
        taxDue = (annualSalary - TAX_CODE_1819) * TAX_BASIC_RATE;
    }

    private void calculateHigherRate(double annualSalary) {
        taxDue = BASIC_RATE_UPPER_LIMIT * TAX_BASIC_RATE + (annualSalary - (TAX_CODE_1819 + BASIC_RATE_UPPER_LIMIT)) * TAX_HIGHER_RATE;
    }

    private void calculateAdvancedRate(double annualSalary) {
        taxDue = (BASIC_RATE_UPPER_LIMIT * TAX_BASIC_RATE) + (HIGHER_RATE_UPPER_LIMIT * TAX_HIGHER_RATE)
                + (annualSalary - (TAX_CODE_1819 + BASIC_RATE_UPPER_LIMIT + HIGHER_RATE_UPPER_LIMIT)) * TAX_ADDITIONAL_RATE;
    }

    private void calculatetaxBands(double annualSalary) {
        if (annualSalary <= TAX_CODE_1819) {
            taxDue = 0;
        } else if (annualSalary > TAX_CODE_1819 && annualSalary <= BASIC_RATE_UPPER_LIMIT) {
            calculateBasicRate(annualSalary);
        } else if (annualSalary > BASIC_RATE_UPPER_LIMIT && annualSalary <= HIGHER_RATE_UPPER_LIMIT) {
            calculateHigherRate(annualSalary);
        } else if (annualSalary > HIGHER_RATE_UPPER_LIMIT) {
            calculateAdvancedRate(annualSalary);
        }
    }

    public void calculateIncomeTax(double taxPaid) {
        String taxStatus;
        String taxOwed = new DecimalFormat("##.##").format(taxDue - taxPaid);
        if (taxDue > taxPaid) {
            taxStatus = ("You have not paid enough tax. You owe £" + taxOwed + "! Your total tax due for the year was £" + taxDue);
        } else if (taxDue < taxPaid) {
            taxStatus = ("You have paid too much tax. You are owed £" + taxOwed + "! Your total tax due for the year was £" + taxDue);

        } else {
            taxStatus = "You have paid the correct amount";
        }
        System.out.println(taxStatus);
    }

    public static void main(String[] args) {
        System.out.println("Have you paid too much income tax? Let's find out! \n" +
                        "Do you have your P60?");
        String p60 = new Scanner(System.in).next();
        if (p60.equalsIgnoreCase("yes") || p60.equalsIgnoreCase("Y")) {
            System.out.println("For the following questions, please use the information provided on your P60. \n" +
                     "What was your total annual salary last year?");
            double annualSalary = new Scanner(System.in).nextDouble();
            System.out.println("How much tax did you pay?");
            double taxPaid = new Scanner(System.in).nextDouble();
            IncomeTaxCalculator incomeTaxCalculator = new IncomeTaxCalculator();
            incomeTaxCalculator.calculatetaxBands(annualSalary);
            incomeTaxCalculator.calculateIncomeTax(taxPaid);
        } else {
            System.out.println("Please come back when you have your P60");
        }
    }
}