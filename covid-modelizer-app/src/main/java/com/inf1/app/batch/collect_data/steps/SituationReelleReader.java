package com.inf1.app.batch.collect_data.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inf1.app.dto.SituationReelleDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class SituationReelleReader implements ItemReader<SituationReelleDTO>{

	private static final Logger LOG = LoggerFactory.getLogger(SituationReelleReader.class);

	@Autowired private RestTemplate restTemplate;
	@Autowired private ObjectMapper objectMapper;
	
	private static final String API_URL_INFOS = "https://www.data.gouv.fr/fr/datasets/r/d2671c6c-c0eb-4e12-b69a-8e8f87fc224c";
    private static final String API_URL_R0 = "https://www.data.gouv.fr/fr/datasets/r/381a9472-ce83-407d-9a64-1b8c23af83df";

    private int nextSituationReelleIndex;
    private int nextR0Index;
    private SituationReelleDTO[] situationReelleData;
    private List<List<String>> r0data;
    
    public SituationReelleReader() {
        nextSituationReelleIndex = 0;
        nextR0Index = 0;
    }
    
	@Override
	public SituationReelleDTO read()
			throws Exception {
		SituationReelleDTO nextSituationReelleDto = null;
		if (situationReelleDataIsNotInitialized()) {
			situationReelleData = fetchSituationReelleDataFromAPI(API_URL_INFOS);
        }
        if (nextSituationReelleIndex < situationReelleData.length) {
        	nextSituationReelleDto = situationReelleData[nextSituationReelleIndex];
        	/*
        	 * Le R0 commence le 18/03/2020 alors que l'API proposant les autres données
        	 * commence le 02/03/2020.
        	 * Ainsi, on décalle pour avoir les 16 premiers jours avec un R0 à vide.
        	 */
        	int nbJoursPlusTard = 16;
            if(nextSituationReelleIndex == nbJoursPlusTard) {
                r0data = fetchR0DataFromAPI(API_URL_R0);
                nextR0Index = 0;
            }
        	if(r0dataIsNotInitialized()) {
        	    nextSituationReelleDto.setR0("");
            } else {
                nextSituationReelleDto.setR0(r0data.get(nextR0Index++).get(1));
            }
            nextSituationReelleIndex++;
        } else {
            nextSituationReelleIndex = 0;
            situationReelleData = null;
        }
        //LOG.info(nextSituationReelleDto == null ? "" : nextSituationReelleDto.toString());
        return nextSituationReelleDto;
	}
	
	private boolean situationReelleDataIsNotInitialized() {
        return this.situationReelleData == null;
    }

    private boolean r0dataIsNotInitialized() { return this.r0data == null; }
	
	private SituationReelleDTO[] fetchSituationReelleDataFromAPI(String url) throws JsonProcessingException {
		objectMapper.registerModule(new JavaTimeModule());
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String data = response.getBody();
        SituationReelleDTO[] lines = objectMapper.readValue(data, SituationReelleDTO[].class);
        return lines;
    }

    private List<List<String>> fetchR0DataFromAPI(String url) {
        objectMapper.registerModule(new JavaTimeModule());
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String data = response.getBody();
        List<List<String>> r0s = new ArrayList<>();
        // Nécessaire pour skip la 1ère ligne (en-tête)
        String[] lines = data.split("\n");
        for(int i = 1 ; i < lines.length ; i++) {
            List<String> dateAndR0 = new ArrayList<>();
            dateAndR0.add(lines[i].split(",")[0]);
            dateAndR0.add(lines[i].split(",")[2]);
            r0s.add(dateAndR0);
        }
        return r0s;
    }
	
	

}
