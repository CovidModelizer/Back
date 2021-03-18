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

@Component
public class RESTSituationReelleReader implements ItemReader<SituationReelleDTO>{

	private static final Logger LOG = LoggerFactory.getLogger(RESTSituationReelleReader.class);

	@Autowired private RestTemplate restTemplate;
	
	@Autowired private ObjectMapper objectMapper;
	
	private static final String API_URL = "https://www.data.gouv.fr/fr/datasets/r/d2671c6c-c0eb-4e12-b69a-8e8f87fc224c";
	private int nextSituationReelleIndex;
    private SituationReelleDTO[] situationReelleData;
    
    public RESTSituationReelleReader() {
        nextSituationReelleIndex = 0;
    }
    
	@Override
	public SituationReelleDTO read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		SituationReelleDTO nextSituationReelleDto = null;
		if (situationReelleDataIsNotInitialized()) {
			situationReelleData = fetchSituationReelleDataFromAPI(API_URL);
        }
        if (nextSituationReelleIndex < situationReelleData.length) {
        	nextSituationReelleDto = situationReelleData[nextSituationReelleIndex];
            nextSituationReelleIndex++;
        } else {
            nextSituationReelleIndex = 0;
            situationReelleData = null;
        }
        LOG.info(nextSituationReelleDto == null ? "" : nextSituationReelleDto.toString());
        return nextSituationReelleDto;
	}
	
	private boolean situationReelleDataIsNotInitialized() {
        return this.situationReelleData == null;
    }
	
	private SituationReelleDTO[] fetchSituationReelleDataFromAPI(String url) throws JsonMappingException, JsonProcessingException {
		objectMapper.registerModule(new JavaTimeModule());
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String data = response.getBody();
        SituationReelleDTO[] lines = objectMapper.readValue(data, SituationReelleDTO[].class);
        return lines;
    }
	
	

}
