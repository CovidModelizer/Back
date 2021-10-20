package calculators.biologic.sir;

import calculators.utils.CalculatorTest;
import com.inf1.app.batch.modelisations.calculators.biologic.sir.InfectionSIRCalculator;
import com.inf1.app.dto.ModelisationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class InfectionSIRCalculatorTest extends CalculatorTest {

    @Test
    public void test() {
        InfectionSIRCalculator c = new InfectionSIRCalculator();
        ModelisationDTO m = c.calculate(situationsReelsDTO);
        Assertions.assertEquals(m.getDateCalcul(), LocalDate.now());
        Assertions.assertEquals(m.getValues().get(LocalDate.of(2021, Month.OCTOBER, 1)), "54927");
        assert (m.getCoeff().get("beta").toString().startsWith("0.0890"));
    }
}