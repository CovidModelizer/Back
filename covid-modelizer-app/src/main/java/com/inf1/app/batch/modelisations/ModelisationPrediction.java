package com.inf1.app.batch.modelisations;

import com.inf1.app.batch.modelisations.calculators.biologic.sir.InfectionSIRCalculator;
import com.inf1.app.batch.modelisations.calculators.biologic.svir.VaccinationSVIRCalculator;
import com.inf1.app.batch.modelisations.calculators.machinelearning.multivariate.InfectionMultivariateCalculator;
import com.inf1.app.batch.modelisations.calculators.machinelearning.multivariate.VaccinationMultivariateCalculator;
import com.inf1.app.batch.modelisations.calculators.machinelearning.univariate.InfectionUnivariateCalculator;
import com.inf1.app.batch.modelisations.calculators.machinelearning.univariate.VaccinationUnivariateCalculator;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.repository.ModelisationRepository;
import com.inf1.app.jpa.repository.SituationReelleRepository;
import com.inf1.app.utils.DatabaseUtils;
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

import java.time.LocalTime;
import java.util.List;

@Component
public class ModelisationPrediction implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(ModelisationPrediction.class);

    @Autowired
    private SituationReelleRepository situationReelleRepository;
    @Autowired
    private ModelisationRepository modelisationRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job recupDonneesQuotidiennesJob;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info(">>> afterPropertiesSet method from ModelisationPrediction class <<<");
        jobLauncher.run(recupDonneesQuotidiennesJob,
                new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());
        calculateAllPredictions();
    }

    @Scheduled(cron = "${batch-calculation.cron}", zone = "${batch.zone}")
    public void calculateAllPredictions() {
        LOG.info(">>> calculateAllPredictions method from ModelisationPrediction class <<<");

        LocalTime start;

        if (DatabaseUtils.getJdbcTemplate() == null) {
            DatabaseUtils.setJdbcTemplate(jdbcTemplate);
        }
        DatabaseUtils.cleanModelisation();

        List<SituationReelleDTO> situationsReellesDTO = situationReelleRepository.findAllDTO();

        LOG.info(">>> Infection predictions");

        start = LocalTime.now();
        LOG.info("> Infection prediction : Univariate Machine Learning");
        InfectionUnivariateCalculator c1 = new InfectionUnivariateCalculator();
        ModelisationDTO infectionUnivariateDTO = c1.calculate(situationsReellesDTO);
        modelisationRepository.persistDTO(infectionUnivariateDTO, "INF", "UNI");
        LOG.info("END - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

        start = LocalTime.now();
        LOG.info("> Infection prediction : Multivariate Machine Learning");
        InfectionMultivariateCalculator c2 = new InfectionMultivariateCalculator();
        ModelisationDTO infectionMultivariateDTO = c2.calculate(situationsReellesDTO);
        modelisationRepository.persistDTO(infectionMultivariateDTO, "INF", "MUL");
        LOG.info("END - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

        start = LocalTime.now();
        LOG.info("> Infection prediction : SIR");
        InfectionSIRCalculator c3 = new InfectionSIRCalculator();
        ModelisationDTO infectionSIRDTO = c3.calculate(situationsReellesDTO);
        modelisationRepository.persistDTO(infectionSIRDTO, "INF", "SIR");
        LOG.info("END - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

        LOG.info(">>> Vaccination predictions");

        start = LocalTime.now();
        LOG.info("> Vaccination prediction : Univariate Machine Learning");
        VaccinationUnivariateCalculator c4 = new VaccinationUnivariateCalculator();
        ModelisationDTO vaccinationUnivariateDTO = c4.calculate(situationsReellesDTO);
        modelisationRepository.persistDTO(vaccinationUnivariateDTO, "VAC", "UNI");
        LOG.info("END - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

        start = LocalTime.now();
        LOG.info("> Vaccination prediction : Multivariate Machine Learning");
        VaccinationMultivariateCalculator c5 = new VaccinationMultivariateCalculator();
        ModelisationDTO vaccinationMultivariateDTO = c5.calculate(situationsReellesDTO);
        modelisationRepository.persistDTO(vaccinationMultivariateDTO, "VAC", "MUL");
        LOG.info("END - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");

        start = LocalTime.now();
        LOG.info("> Vaccination prediction : SVIR");
        VaccinationSVIRCalculator c6 = new VaccinationSVIRCalculator();
        ModelisationDTO vaccinationSVIRDTO = c6.calculate(situationsReellesDTO);
        modelisationRepository.persistDTO(vaccinationSVIRDTO, "VAC", "SVI");
        LOG.info("END - " + LocalTime.now().minusNanos(start.toNanoOfDay()) + "ms");
    }
}