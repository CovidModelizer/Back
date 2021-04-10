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
import com.inf1.app.batch.modelisations.calculators.InfectionMachineLearningCalculator;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

public class InfectionMachineLearningCalculatorTest {

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
		InfectionMachineLearningCalculator c = new InfectionMachineLearningCalculator();
		ModelisationDTO m = c.calculate(situationsReelsDTO);
		assertEquals(m.getDateCalcul(), LocalDate.now());
		assert (Integer.parseInt(m.getValues().get(LocalDate.of(2021, Month.APRIL, 9))) >= 53749
				&& Integer.parseInt(m.getValues().get(LocalDate.of(2021, Month.APRIL, 9))) <= 53751);
		assert (m.getCoeff().get("PredJ+1_constante").toString().startsWith("-5766.63"));
	}

}
