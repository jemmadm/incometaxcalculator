import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncomeTaxCalculatorTest {

    private IncomeTaxCalculator incomeTaxCalculator = new IncomeTaxCalculator();

    @Test
    public void checkNoTaxDueOnAnnualSalaryUnderAllowance(){
        assertThat(incomeTaxCalculator.calculatetaxRateToUse(1000)).isEqualTo(0);
    }

    @Test
    public void checkBasicTaxRateDueOnSalaryUnder34501(){
        assertThat(incomeTaxCalculator.calculatetaxRateToUse(20000)).isEqualTo(1630);
    }

    @Test
    public void checkHigherTaxRateDueOnSalaryUnder150000(){
        assertThat(incomeTaxCalculator.calculatetaxRateToUse(90000)).isEqualTo(24360);
    }

    @Test
    public void checkAdditionalTaxRateDueOnSalaryOver150000(){
        assertThat(incomeTaxCalculator.calculatetaxRateToUse(200000)).isEqualTo(68542.5);
    }

    @Test
    public void checkTaxUnderpayment(){
        assertThat(incomeTaxCalculator.calculateTaxOwed(900,1000)).isEqualTo("You have not paid enough tax. You owe £100! Your total tax due for the year was £1000");
    }

    @Test
    public void checkTaxOverpayment(){
        assertThat(incomeTaxCalculator.calculateTaxOwed(2000, 1000)).isEqualTo("You have paid too much tax. You are owed £1000! Your total tax due for the year was £1000");
    }

    @Test
    public void checkTaxCorrect(){
        assertThat(incomeTaxCalculator.calculateTaxOwed(1630, 1630)).isEqualTo("You have paid the correct amount of tax, £1630");
    }
}