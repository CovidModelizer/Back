package com.inf1.app.batch.data.collect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;

import com.inf1.app.dto.CollectSituationReelleDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.utils.DTOUtils;
import com.inf1.app.utils.DatabaseUtils;

@Component
public class SituationReelleReader implements ItemReader<SituationReelleDTO> {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(SituationReelleReader.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	JdbcTemplate jdbcTemplate;

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
	public SituationReelleDTO read() throws Exception {
		SituationReelleDTO nextSituationReelleDto = null;
		if (situationReelleDataIsNotInitialized()) {
			situationReelleData = fetchSituationReelleDataFromAPI(API_URL_INFOS);
			deletePreviousData();
		}
		if (nextSituationReelleIndex < situationReelleData.length)

		{
			nextSituationReelleDto = situationReelleData[nextSituationReelleIndex];
			/*
			 * Le R0 commence le 18/03/2020 alors que l'API proposant les autres données
			 * commence le 02/03/2020. Ainsi, on décale pour avoir les 16 premiers jours
			 * avec un R0 à vide.
			 */
			int nbJoursPlusTard = 16;
			if (nextSituationReelleIndex == nbJoursPlusTard) {
				r0data = fetchR0DataFromAPI(API_URL_R0);
			}
			String val = null;
			if (r0dataIsInitialized()) {
				if (!r0data.get(nextR0Index).get(1).equals("NA")) {
					val = r0data.get(nextR0Index).get(1);
				}
				nextR0Index++;
			}
			nextSituationReelleDto.setR0(val);
			nextSituationReelleIndex++;
		} else {
			nextSituationReelleIndex = 0;
			situationReelleData = null;
			nextR0Index = 0;
			r0data = null;
		}
		return nextSituationReelleDto;
	}

	private boolean situationReelleDataIsNotInitialized() {
		return this.situationReelleData == null;
	}

	private boolean r0dataIsInitialized() {
		return this.r0data != null;
	}

	private SituationReelleDTO[] fetchSituationReelleDataFromAPI(String url) throws JsonProcessingException {
		objectMapper.registerModule(new JavaTimeModule());
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String data = response.getBody();
		CollectSituationReelleDTO[] collect = objectMapper.readValue(data, CollectSituationReelleDTO[].class);
		SituationReelleDTO[] lines = DTOUtils.collectToArrayOfSituationReelleDTOMapper(collect);
		return lines;
	}

	private List<List<String>> fetchR0DataFromAPI(String url) {
		objectMapper.registerModule(new JavaTimeModule());
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String data = response.getBody();
		List<List<String>> r0s = new ArrayList<>();
		// Nécessaire pour skip la 1ère ligne (en-tête)
		String[] lines = data.split("\n");
		for (int i = 1; i < lines.length; i++) {
			List<String> dateAndR0 = new ArrayList<>();
			dateAndR0.add(lines[i].split(",")[0]);
			dateAndR0.add(lines[i].split(",")[2]);
			r0s.add(dateAndR0);
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