package com.inf1.app.batch;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import com.inf1.app.batch.collect_data.steps.SituationReelleReader;
import com.inf1.app.batch.collect_data.steps.SituationReelleProcessor;
import com.inf1.app.dto.SituationReelleDTO;

@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class BatchConfiguration {
		
    @Bean
	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		return new RestTemplate();
    }
    
	@Bean
    ItemReader<SituationReelleDTO> reader() {
		return new SituationReelleReader();
    }
	
	@Bean
	ItemProcessor<SituationReelleDTO, SituationReelleDTO> processor(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		return new SituationReelleProcessor(dataSource, jdbcTemplate);
	}

	@Bean
	JdbcBatchItemWriter<SituationReelleDTO> writer(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<SituationReelleDTO>()
	    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO situation_reelle (r0, cas_confirmes, deces, deces_ehpad, reanimation, hospitalises, "
	    		+ "gueris, date, nouvelles_hospitalisations, nouvelles_reanimations, nouvelles_premieres_injections, "
	    		+ "cumul_premieres_injections, stock_nombre_total_doses, stock_nombre_doses_pfizer, "
	    		+ "stock_nombre_doses_moderna, livraisons_cumul_nombre_total_doses, livraisons_cumul_nombre_doses_pfizer, "
	    		+ "livraisons_cumul_nombre_doses_moderna, total_prises_rendez_vous_semaine, "
	    		+ "prises_rendez_vous_semaine_rang1, prises_rendez_vous_semaine_rang2, stock_ehpad_nombre_doses_pfizer) "
	    		+ "VALUES (:r0, :casConfirmes, :deces, :decesEhpad, :reanimation, :hospitalises, :gueris, :date, :nouvellesHospitalisations, "
	    		+ ":nouvellesReanimations, :nouvellesPremieresInjections, :cumulPremieresInjections, "
	    		+ ":stockNombreTotalDoses, :stockNombreDosesPfizer, :stockNombreDosesModerna, "
	    		+ ":livraisonsCumulNombreTotalDoses, :livraisonsCumulNombreDosesPfizer, "
	    		+ ":livraisonsCumulNombreDosesModerna, :totalPrisesRendezVousSemaine, :prisesRendezVousSemaineRang1, "
	    		+ ":prisesRendezVousSemaineRang2, :stockEhpadNombreDosesPfizer)")
	    .dataSource(dataSource)
	    .build();
	}
	
	@Bean
	Step situationsReellesStep(ItemReader<SituationReelleDTO> reader, 
			ItemProcessor<SituationReelleDTO, SituationReelleDTO> processor,
			JdbcBatchItemWriter<SituationReelleDTO> writer,
			StepBuilderFactory stepBuilderFactory) {
	  return stepBuilderFactory.get("situationsReellesStep")
	    .<SituationReelleDTO, SituationReelleDTO> chunk(10000)
	    .reader(reader)
	    .processor(processor)
	    .writer(writer)
	    .build();
	}
	
	@Bean
	Job recupDonneesQuotidiennesJob(JobBuilderFactory jobBuilderFactory,
			@Qualifier("situationsReellesStep") Step situationsReellesStep) {
	  return jobBuilderFactory.get("recupDonneesQuotidiennesJob")
	    .incrementer(new RunIdIncrementer())
	    .flow(situationsReellesStep)
	    .end()
	    .build();
	}
	
}
