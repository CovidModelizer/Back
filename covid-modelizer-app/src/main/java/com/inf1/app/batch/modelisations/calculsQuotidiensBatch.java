package com.inf1.app.batch.modelisations;

import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inf1.app.batch.modelisations.calculators.CasLineaireCalculator;
import com.inf1.app.batch.modelisations.calculators.CasMachineLearningCalculator;
import com.inf1.app.batch.modelisations.calculators.CasSIRCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinSVIRCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinLineaireCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinMachineLearningCalculator;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.repository.ModelisationRepository;
import com.inf1.app.jpa.repository.SituationReelleRepository;

@Component
public class calculsQuotidiensBatch {

	private static final Logger LOG = LoggerFactory.getLogger(calculsQuotidiensBatch.class);

	@Autowired
	SituationReelleRepository situationReelleRepository;
	@Autowired
	ModelisationRepository modelisationRepository;

	@Scheduled(cron = "0 28 18 * * *")
	public void calculerData() {
		LOG.info(">>> MODELES DE PREDICTIONS <<<");

		LocalTime start = null;

		List<SituationReelleDTO> situationsReellesDTO = situationReelleRepository.findallDTO();

		LOG.info(">>> Prediction cas");

		start = LocalTime.now();
		LOG.info("> Prediction cas : Lineaire"); // DONE
		CasLineaireCalculator c1 = new CasLineaireCalculator();
		ModelisationDTO casLineaireDTO = c1.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(casLineaireDTO, "CAS", "LIN");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction cas : MachineLearning"); // DONE
		CasMachineLearningCalculator c2 = new CasMachineLearningCalculator();
		ModelisationDTO casMachineLearningDTO = c2.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(casMachineLearningDTO, "CAS", "MCL");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction cas : SIR"); // TODO
		CasSIRCalculator c3 = new CasSIRCalculator();
		ModelisationDTO casSIRDTO = c3.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(casSIRDTO, "CAS", "SIR");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		LOG.info(">>> Prediction vaccin");

		start = LocalTime.now();
		LOG.info("> Prediction vaccin : Lineaire"); // DONE
		VaccinLineaireCalculator v1 = new VaccinLineaireCalculator();
		ModelisationDTO vaccinLineaireDTO = v1.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(vaccinLineaireDTO, "VAC", "LIN");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction vaccin : MachineLearning"); // DONE
		VaccinMachineLearningCalculator v3 = new VaccinMachineLearningCalculator();
		ModelisationDTO vaccinMachineLearningDTO = v3.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(vaccinMachineLearningDTO, "VAC", "MCL");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction vaccin : SVIR"); // TODO
		VaccinSVIRCalculator c4 = new VaccinSVIRCalculator();
		ModelisationDTO vaccinSVIRDTO = c4.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(vaccinSVIRDTO, "VAC", "SVR");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");
	}
}
