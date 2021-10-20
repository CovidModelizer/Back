package calculators.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inf1.app.dto.SituationReelleDTO;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class CalculatorTest {

    protected static List<SituationReelleDTO> situationsReelsDTO;

    protected static List<SituationReelleDTO> initData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        SituationReelleDTO[] lines = objectMapper.readValue(new File("src/test/resources/m03y20-m10y21_data.json"),
                SituationReelleDTO[].class);
        return Arrays.asList(lines);
    }

    @BeforeAll
    protected static void init() throws IOException {
        situationsReelsDTO = CalculatorTest.initData();
    }
}