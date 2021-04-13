package com.inf1.app.batch.modelisations;

import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inf1.app.batch.modelisations.calculators.InfectionLinearCalculator;
import com.inf1.app.batch.modelisations.calculators.InfectionMachineLearningCalculator;
import com.inf1.app.batch.modelisations.calculators.InfectionSIRCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinationSVIRCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinationLinearCalculator;
import com.inf1.app.batch.modelisations.calculators.VaccinationMachineLearningCalculator;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.repository.ModelisationRepository;
import com.inf1.app.jpa.repository.SituationReelleRepository;
import com.inf1.app.utils.DatabaseUtils;

@Component
public class PredictionCalculation implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(PredictionCalculation.class);

	@Autowired
	SituationReelleRepository situationReelleRepository;
	@Autowired
	ModelisationRepository modelisationRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job recupDonneesQuotidiennesJob;

	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info(">>> afterPropertiesSet from PredictionCalculation <<<");
		jobLauncher.run(recupDonneesQuotidiennesJob,
				new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());
		calculatePrediction();
	}

	@Scheduled(cron = "${batch-calculation.cron}", zone = "${batch.zone}")
	public void calculatePrediction() {
		LOG.info(">>> calculatePrediction <<<");

		LocalTime start = null;

		if (DatabaseUtils.getJdbcTemplate() == null) {
			DatabaseUtils.setJdbcTemplate(jdbcTemplate);
		}
		DatabaseUtils.cleanModelisation();

		List<SituationReelleDTO> situationsReellesDTO = situationReelleRepository.findallDTO();

		LOG.info(">>> Prediction infection");

		start = LocalTime.now();
		LOG.info("> Prediction infection : Lineaire");
		InfectionLinearCalculator c1 = new InfectionLinearCalculator();
		ModelisationDTO casLineaireDTO = c1.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(casLineaireDTO, "INF", "LIN");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction infection : MachineLearning");
		InfectionMachineLearningCalculator c2 = new InfectionMachineLearningCalculator();
		ModelisationDTO casMachineLearningDTO = c2.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(casMachineLearningDTO, "INF", "MCL");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction infection : SIR");
		InfectionSIRCalculator c3 = new InfectionSIRCalculator();
		ModelisationDTO casSIRDTO = c3.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(casSIRDTO, "INF", "SIR");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		LOG.info(">>> Prediction vaccination");

		start = LocalTime.now();
		LOG.info("> Prediction vaccination : Lineaire");
		VaccinationLinearCalculator v1 = new VaccinationLinearCalculator();
		ModelisationDTO vaccinLineaireDTO = v1.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(vaccinLineaireDTO, "VAC", "LIN");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction vaccination : MachineLearning");
		VaccinationMachineLearningCalculator v3 = new VaccinationMachineLearningCalculator();
		ModelisationDTO vaccinMachineLearningDTO = v3.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(vaccinMachineLearningDTO, "VAC", "MCL");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

		start = LocalTime.now();
		LOG.info("> Prediction vaccination : SVIR");
		VaccinationSVIRCalculator c4 = new VaccinationSVIRCalculator();
		ModelisationDTO vaccinSVIRDTO = c4.calculate(situationsReellesDTO);
		modelisationRepository.persistDTO(vaccinSVIRDTO, "VAC", "SVR");
		LOG.info("FIN - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");
	}

}
