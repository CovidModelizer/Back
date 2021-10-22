package com.inf1.app.batch.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inf1.app.dto.CollectSituationReelleDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.utils.DTOUtils;
import com.inf1.app.utils.DatabaseUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SituationReelleReader implements ItemReader<SituationReelleDTO> {

    private final static String API_URL_INFOS =
            "https://www.data.gouv.fr/fr/datasets/r/d2671c6c-c0eb-4e12-b69a-8e8f87fc224c";
    private final static String API_URL_R0 =
            "https://www.data.gouv.fr/fr/datasets/r/381a9472-ce83-407d-9a64-1b8c23af83df";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    private int
            nextSituationReelleIndex,
            nextR0Index;
    private SituationReelleDTO[] situationReelleData;
    private List<List<String>> r0data;

    public SituationReelleReader() {
        nextSituationReelleIndex = 0;
        nextR0Index = 0;
    }

    @Override
    public SituationReelleDTO read() throws Exception {
        SituationReelleDTO nextSituationReelleDto = null;
        if (situationReelleData == null) {
            situationReelleData = fetchSituationReelleDataFromAPI();
            deletePreviousData();
        }
        if (nextSituationReelleIndex < situationReelleData.length) {
            nextSituationReelleDto = situationReelleData[nextSituationReelleIndex];
            /*
             * The R0 begins on 03/18/2020 while the API where data come from begins
             * on 03/02/2020. Thus, we look for the R0 starting from 16 days after
             * the beginning of data provided by the API.
             */
            int r0StartIndex = 16;
            if (nextSituationReelleIndex == r0StartIndex) {
                r0data = fetchR0DataFromAPI();
            }
            String r0Value = null;
            if (r0data != null && nextR0Index < r0data.size()) {
                if (!r0data.get(nextR0Index).get(1).equals("NA")) {
                    r0Value = r0data.get(nextR0Index).get(1);
                }
                nextR0Index++;
            }
            nextSituationReelleDto.setR0(r0Value);
            nextSituationReelleIndex++;
        } else {
            nextSituationReelleIndex = 0;
            situationReelleData = null;
            nextR0Index = 0;
            r0data = null;
        }
        return nextSituationReelleDto;
    }

    private SituationReelleDTO[] fetchSituationReelleDataFromAPI() throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL_INFOS, String.class);
        String data = response.getBody();
        CollectSituationReelleDTO[] collect = objectMapper.readValue(data, CollectSituationReelleDTO[].class);
        return DTOUtils.collectToArrayOfSituationReelleDTOMapper(collect);
    }

    private List<List<String>> fetchR0DataFromAPI() {
        objectMapper.registerModule(new JavaTimeModule());
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL_R0, String.class);
        String data = response.getBody();
        List<List<String>> r0s = new ArrayList<>();
        if (data != null) {
            String[] lines = data.split("\n");
            // Skipping the first line of data titles
            for (int i = 1; i < lines.length; i++) {
                List<String> dateAndR0 = new ArrayList<>();
                dateAndR0.add(lines[i].split(",")[0]);
                dateAndR0.add(lines[i].split(",")[2]);
                r0s.add(dateAndR0);
            }
        }
        return r0s;
    }

    private void deletePreviousData() {
        if (DatabaseUtils.getJdbcTemplate() == null) {
            DatabaseUtils.setJdbcTemplate(jdbcTemplate);
        }
        DatabaseUtils.cleanSituationReelle();
    }
}