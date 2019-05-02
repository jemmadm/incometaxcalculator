public class IncomeTaxCalculator {

    public static final double TAX_STANDARD_RATE = 0.2;
    public double annualSalary;
    public double taxCode;
    public double taxPayableSalary = annualSalary - (taxCode*10);
    public double taxDue = taxPayableSalary*TAX_STANDARD_RATE;
    public String taxStatus;

    public String calculateIncomeTax(double annualSalary, double taxPaid, double taxCode) {
        if (taxDue > taxPaid) {
            taxStatus = ("You have not paid enough tax. You owe" + (taxDue - taxPaid));
        } else if (taxDue < taxPaid) {
            taxStatus = ("You have paid too much tax. You are owed" + (taxDue - taxPaid));
        } else {
            taxStatus = "You have paid the correct amount";
        }
        return taxStatus;
    }
}