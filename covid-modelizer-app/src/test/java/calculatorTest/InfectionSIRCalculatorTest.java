package calculatorTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inf1.app.batch.modelisations.calculators.InfectionSIRCalculator;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

public class InfectionSIRCalculatorTest {

	static List<SituationReelleDTO> situationsReelsDTO;

	@BeforeAll
	public static void init() throws IOException {
		situationsReelsDTO = new ArrayList<SituationReelleDTO>();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		SituationReelleDTO[] lines = objectMapper.readValue(new File("src/test/resources/data.json"),
				SituationReelleDTO[].class);
		for (int i = 0; i < lines.length; i++) {
			situationsReelsDTO.add(lines[i]);
		}
	}

	@Test
	public void test() {
		InfectionSIRCalculator c = new InfectionSIRCalculator();
		ModelisationDTO m = c.calculate(situationsReelsDTO);
		assertEquals(m.getDateCalcul(), LocalDate.now());
		assertEquals(m.getValues().get(LocalDate.of(2021, Month.APRIL, 9)), "342213");
		assertEquals(m.getCoeff().get("beta").toString(), "0.118");
	}

}