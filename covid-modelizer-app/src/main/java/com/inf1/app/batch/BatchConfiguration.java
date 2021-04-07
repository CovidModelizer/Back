package com.inf1.app.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.inf1.app.batch.data.collect.SituationReelleProcessor;
import com.inf1.app.batch.data.collect.SituationReelleReader;
import com.inf1.app.dto.SituationReelleDTO;

@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class BatchConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	ItemReader<SituationReelleDTO> reader() {
		return new SituationReelleReader();
	}

	@Bean
	ItemProcessor<SituationReelleDTO, SituationReelleDTO> processor() {
		return new SituationReelleProcessor();
	}

	@Bean
	JdbcBatchItemWriter<SituationReelleDTO> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<SituationReelleDTO>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO situation_reelle (date, r0, cumul_cas_confirmes, nouveaux_cas_confirmes, cumul_cas_confirmes_ehpad, "
						+ "nouveaux_cas_confirmes_ehpad, cas_possibles_ehpad, cumul_gueris, nouveaux_gueris, cumul_deces, "
						+ "nouveaux_deces, cumul_deces_ehpad, nouveaux_deces_ehpad, reanimation, hospitalises, tests_realises, "
						+ "tests_positifs, nouvelles_hospitalisations, nouvelles_reanimations, cumul_premieres_injections, "
						+ "nouvelles_premieres_injections, stock_nombre_total_doses, stock_nombre_doses_pfizer, "
						+ "stock_nombre_doses_moderna, stock_ehpad_nombre_doses_pfizer, cumul_livraisons_nombre_total_doses, "
						+ "nouvelles_livraisons_nombre_total_doses, cumul_livraisons_nombre_doses_pfizer, "
						+ "nouvelles_livraisons_nombre_doses_pfizer, cumul_livraisons_nombre_doses_moderna, "
						+ "nouvelles_livraisons_nombre_doses_moderna, total_prises_rendez_vous_semaine, prises_rendez_vous_semaine_rang1, "
						+ "prises_rendez_vous_semaine_rang2, sir_s, sir_i, sir_r, svir_s, svir_v, svir_i, svir_r, svir_nouveau_taux_vaccination) "
						+ "VALUES (:date, :r0, :cumulCasConfirmes, :nouveauxCasConfirmes, :cumulCasConfirmesEhpad, "
						+ ":nouveauxCasConfirmesEhpad, :casPossiblesEhpad, :cumulGueris, :nouveauxGueris, :cumulDeces, "
						+ ":nouveauxDeces, :cumulDecesEhpad, :nouveauxDecesEhpad, :reanimation, :hospitalises, :testsRealises, "
						+ ":testsPositifs, :nouvellesHospitalisations, :nouvellesReanimations, :cumulPremieresInjections, "
						+ ":nouvellesPremieresInjections, :stockNombreTotalDoses, :stockNombreDosesPfizer, "
						+ ":stockNombreDosesModerna, :stockEhpadNombreDosesPfizer, :cumulLivraisonsNombreTotalDoses, "
						+ ":nouvellesLivraisonsNombreTotalDoses, :cumulLivraisonsNombreDosesPfizer, "
						+ ":nouvellesLivraisonsNombreDosesPfizer, :cumulLivraisonsNombreDosesModerna, "
						+ ":nouvellesLivraisonsNombreDosesModerna, :totalPrisesRendezVousSemaine, :prisesRendezVousSemaineRang1, "
						+ ":prisesRendezVousSemaineRang2, :sirS, :sirI, :sirR, :svirS, :svirV, :svirI, :svirR, "
						+ ":svirNouveauTauxVaccination)")
				.dataSource(dataSource).build();
	}

	@Bean
	Step situationsReellesStep(ItemReader<SituationReelleDTO> reader,
			ItemProcessor<SituationReelleDTO, SituationReelleDTO> processor,
			JdbcBatchItemWriter<SituationReelleDTO> writer, StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("situationsReellesStep").<SituationReelleDTO, SituationReelleDTO>chunk(10000)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean
	Job recupDonneesQuotidiennesJob(JobBuilderFactory jobBuilderFactory,
			@Qualifier("situationsReellesStep") Step situationsReellesStep) {
		return jobBuilderFactory.get("recupDonneesQuotidiennesJob").incrementer(new RunIdIncrementer())
				.flow(situationsReellesStep).end().build();
	}

}
