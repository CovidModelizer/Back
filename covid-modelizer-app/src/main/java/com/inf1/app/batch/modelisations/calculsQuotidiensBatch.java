package com.inf1.app.batch.modelisations;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inf1.app.dto.GlobalStep2DTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.repositories.CoeffLineaireRepository;
import com.inf1.app.jpa.repositories.SituationReelleRepository;

@Component
public class calculsQuotidiensBatch {

	 private static final Logger LOG = LoggerFactory.getLogger(calculsQuotidiensBatch.class);

	 @Autowired SituationReelleRepository situationReelleRepository;
	 @Autowired CoeffLineaireRepository coeffLineaireRepository;
	 
	 @Scheduled(cron = "0 28 18 * * *")
	 public void calculerData() {
		 LOG.info("$$$ MODELES DE PREDICTIONS $$$");
		 List<SituationReelleDTO> situationsReellesDTO = situationReelleRepository.findallDTO();
		 
		 GlobalStep2DTO coeffLineaireDTO = new GlobalStep2DTO();
		 // TODO : créer les dtos pour chaque table
		 
		 // TODO : créer daos
		 // TODO : faire les requêtes dans des transactions
		 
		 
		 return;
	 }
}
