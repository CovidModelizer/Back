package com.inf1.app.batch.steps;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.inf1.app.dto.SituationReelleDTO;

public class RESTSituationReelleReader implements ItemReader<SituationReelleDTO>{

	private static final Logger LOG = LoggerFactory.getLogger(RESTSituationReelleReader.class);

	private final String apiUrl;
    private final RestTemplate restTemplate;
    private int nextStudentIndex;
    private List<SituationReelleDTO> situationReelleData;
    
    public RESTSituationReelleReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextStudentIndex = 0;
    }
    
	@Override
	public SituationReelleDTO read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (situationReelleDataIsNotInitialized()) {
			situationReelleData = fetchSituationReelleDataFromAPI();
        }
		SituationReelleDTO nextSituationReelle = null;
        if (nextStudentIndex < situationReelleData.size()) {
        	nextSituationReelle = situationReelleData.get(nextStudentIndex);
            nextStudentIndex++;
        } else {
            nextStudentIndex = 0;
            situationReelleData = null;
        }
        return nextSituationReelle;
	}
	
	private boolean situationReelleDataIsNotInitialized() {
        return this.situationReelleData == null;
    }
	
	private List<SituationReelleDTO> fetchSituationReelleDataFromAPI() {
        ResponseEntity<SituationReelleDTO[]> response = restTemplate.getForEntity(apiUrl,
        		SituationReelleDTO[].class
        );
        SituationReelleDTO[] studentData = response.getBody();
        return Arrays.asList(studentData);
    }
	
	

}
