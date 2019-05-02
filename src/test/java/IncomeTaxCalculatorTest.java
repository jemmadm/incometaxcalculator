import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class IncomeTaxCalculatorTest {

    private IncomeTaxCalculator incomeTaxCalculator = new IncomeTaxCalculator();

    @Test

    public void checkTaxUnderpayment(){

        assertThat(incomeTaxCalculator.calculateIncomeTax(20_000,700,1150L)).isEqualTo("You have not paid enough tax. You owe " + 1000);
    }

    @Test

    public void checkTaxOverpayment(){

        assertThat(incomeTaxCalculator.calculateIncomeTax(20_000,2700,1150L)).isEqualTo("You have paid too much tax. You are owed " + 1000);
    }

    @Test

    public void checkTaxCorrect(){

        assertThat(incomeTaxCalculator.calculateIncomeTax(20_000,1700,1150L)).isEqualTo("You have paid the correct amount");
    }

}