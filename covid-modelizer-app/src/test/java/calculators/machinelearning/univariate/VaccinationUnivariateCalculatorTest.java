package calculators.machinelearning.univariate;

import calculators.utils.CalculatorTest;
import com.inf1.app.batch.modelisations.calculators.machinelearning.univariate.VaccinationUnivariateCalculator;
import com.inf1.app.dto.ModelisationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class VaccinationUnivariateCalculatorTest extends CalculatorTest {

    @Test
    public void test() {
        VaccinationUnivariateCalculator c = new VaccinationUnivariateCalculator();
        ModelisationDTO m = c.calculate(situationsReelsDTO);
        Assertions.assertEquals(m.getDateCalcul(), LocalDate.now());
        Assertions.assertEquals(m.getValues().get(LocalDate.of(2021, Month.OCTOBER, 1)), "50439867");
        assert (m.getCoeff().get("PredJ+1_constante").toString().startsWith("401427.6034"));
    }
}