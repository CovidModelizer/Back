package com.inf1.app.batch.modelisations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inf1.app.batch.modelisations.calculators.CasLineaireCalculator;
import com.inf1.app.batch.modelisations.calculators.CasMachineLearningCalculator;
import com.inf1.app.batch.modelisations.calculators.CasSIRCalculator;
import com.inf1.app.batch.modelisations.calculators.CasSVIRCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinLineaireCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinLogCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinMachineLearningCalculator;
import com.inf1.app.dto.CasLineaireDTO;
import com.inf1.app.dto.CasMachineLearningDTO;
import com.inf1.app.dto.CasSIRDTO;
import com.inf1.app.dto.CasSVIRDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.dto.VaccinLineaireDTO;
import com.inf1.app.dto.VaccinLogDTO;
import com.inf1.app.dto.VaccinMachineLearningDTO;
import com.inf1.app.jpa.repository.CasLineaireRepository;
import com.inf1.app.jpa.repository.CasMachineLearningRepository;
import com.inf1.app.jpa.repository.CasSIRRespository;
import com.inf1.app.jpa.repository.CasSVIRRepository;
import com.inf1.app.jpa.repository.SituationReelleRepository;
import com.inf1.app.jpa.repository.VaccinLineaireRepository;
import com.inf1.app.jpa.repository.VaccinLogRepository;
import com.inf1.app.jpa.repository.VaccinMachineLearningRepository;

@Component
public class calculsQuotidiensBatch {

	 private static final Logger LOG = LoggerFactory.getLogger(calculsQuotidiensBatch.class);

	 @Autowired SituationReelleRepository situationReelleRepository;
	 @Autowired CasLineaireRepository casLineaireRepository;
	 @Autowired CasMachineLearningRepository casMachineLearningRepository;
	 @Autowired CasSIRRespository casSIRRepository;
	 @Autowired CasSVIRRepository casSVIRRepository;
	 @Autowired VaccinLineaireRepository vaccinLineaireRepository;
	 @Autowired VaccinLogRepository vaccinLogRepository;
	 @Autowired VaccinMachineLearningRepository vaccinMachineLearningRepository;
	 
	 @Scheduled(cron = "0 28 18 * * *")
	 public void calculerData() {
		 LOG.info("$$$ MODELES DE PREDICTIONS $$$");
		 
		 long start;
		 
		 List<SituationReelleDTO> situationsReellesDTO = situationReelleRepository.findallDTO();
		 
		 LOG.info(">>> Prediction cas");
		 
		 start = System.currentTimeMillis();
		 LOG.info("> Prediction cas : Lineaire");
		 CasLineaireCalculator c1 = new CasLineaireCalculator();
		 CasLineaireDTO casLineaireDTO = c1.calculate(situationsReellesDTO);
		 casLineaireRepository.persistDTO(casLineaireDTO);
		 LOG.info("FIN - " + (System.currentTimeMillis() - start) + "ms");
		 
		 start = System.currentTimeMillis();
		 LOG.info("> Prediction cas : MachineLearning");
		 CasMachineLearningCalculator c2 = new CasMachineLearningCalculator();
		 CasMachineLearningDTO casMachineLearningDTO = c2.calculate(situationsReellesDTO);
		 casMachineLearningRepository.persistDTO(casMachineLearningDTO);
		 LOG.info("FIN - " + (System.currentTimeMillis() - start) + "ms");
		 
		 
		 start = System.currentTimeMillis();
		 LOG.info("> Prediction cas : SIR");
		 CasSIRCalculator c3 = new CasSIRCalculator();
		 CasSIRDTO casSIRDTO = c3.calculate(situationsReellesDTO);
		 casSIRRepository.persistDTO(casSIRDTO);
		 LOG.info("FIN - " + (System.currentTimeMillis() - start) + "ms");
		 
		 start = System.currentTimeMillis();
		 LOG.info("> Prediction cas : SVIR");
		 CasSVIRCalculator c4 = new CasSVIRCalculator();
		 CasSVIRDTO casSVIRDTO = c4.calculate(situationsReellesDTO);
		 casSVIRRepository.persistDTO(casSVIRDTO);
		 LOG.info("FIN - " + (System.currentTimeMillis() - start) + "ms");
		 
		 
		 
		 LOG.info(">>> Prediction vaccin");
		 
		 start = System.currentTimeMillis();
		 LOG.info("> Prediction vaccin : Lineaire");
		 VaccinLineaireCalculator v1 = new VaccinLineaireCalculator();
		 VaccinLineaireDTO vaccinLineaireDTO = v1.calculate(situationsReellesDTO);
		 vaccinLineaireRepository.persistDTO(vaccinLineaireDTO);
		 LOG.info("FIN - " + (System.currentTimeMillis() - start) + "ms");
		 
		 start = System.currentTimeMillis();
		 LOG.info("> Prediction vaccin : Log");
		 VaccinLogCalculator v2 = new VaccinLogCalculator();
		 VaccinLogDTO vaccinLogDTO = v2.calculate(situationsReellesDTO);
		 vaccinLogRepository.persistDTO(vaccinLogDTO);
		 LOG.info("FIN - " + (System.currentTimeMillis() - start) + "ms");
		 
		 start = System.currentTimeMillis();
		 LOG.info("> Prediction vaccin : MachineLearning");
		 VaccinMachineLearningCalculator v3 = new VaccinMachineLearningCalculator();
		 VaccinMachineLearningDTO vaccinMachineLearningDTO = v3.calculate(situationsReellesDTO);
		 vaccinMachineLearningRepository.persistDTO(vaccinMachineLearningDTO);
		 LOG.info("FIN - " + (System.currentTimeMillis() - start) + "ms");
	 }
}
