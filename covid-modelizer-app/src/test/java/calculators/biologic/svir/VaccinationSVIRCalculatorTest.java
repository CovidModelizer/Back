package calculators.biologic.svir;

import calculators.utils.CalculatorTest;
import com.inf1.app.batch.modelisations.calculators.biologic.svir.VaccinationSVIRCalculator;
import com.inf1.app.dto.ModelisationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class VaccinationSVIRCalculatorTest extends CalculatorTest {

    @Test
    public void test() {
        VaccinationSVIRCalculator c = new VaccinationSVIRCalculator();
        ModelisationDTO m = c.calculate(situationsReelsDTO);
        Assertions.assertEquals(m.getDateCalcul(), LocalDate.now());
        Assertions.assertEquals(m.getValues().get(LocalDate.of(2021, Month.OCTOBER, 1)), "50404608");
        assert (m.getCoeff().get("beta").toString().startsWith("0.0890"));
    }
}