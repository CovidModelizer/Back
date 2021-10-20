package calculators.machinelearning.multivariate;

import calculators.utils.CalculatorTest;
import com.inf1.app.batch.modelisations.calculators.machinelearning.multivariate.VaccinationMultivariateCalculator;
import com.inf1.app.dto.ModelisationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class VaccinationMultivariateCalculatorTest extends CalculatorTest {

    @Test
    public void test() {
        VaccinationMultivariateCalculator c = new VaccinationMultivariateCalculator();
        ModelisationDTO m = c.calculate(situationsReelsDTO);
        Assertions.assertEquals(m.getDateCalcul(), LocalDate.now());
        Assertions.assertEquals(m.getValues().get(LocalDate.of(2021, Month.OCTOBER, 1)), "49544");
        assert (m.getCoeff().get("PredJ+1_constante").toString().startsWith("4930588.5991"));
    }
}