package com.inf1.app.batch;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.inf1.app.batch.collect_data.steps.RESTSituationReelleReader;
import com.inf1.app.batch.modelisations.steps.ModelisationsItemProcessor;
import com.inf1.app.batch.modelisations.steps.ModelisationsItemWriter;
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
    ItemReader<SituationReelleDTO> restItemReader() {
		return new RESTSituationReelleReader();
    }

	@Bean
	JdbcBatchItemWriter<SituationReelleDTO> restItemWriter(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<SituationReelleDTO>()
	    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO situation_reelle (cas_confirmes, deces, deces_ehpad, reanimation, hospitalises, "
	    		+ "gueris, date, nouvelles_hospitalisations, nouvelles_reanimations, nouvelles_premieres_injections, "
	    		+ "cumul_premieres_injections, stock_nombre_total_doses, stock_nombre_doses_pfizer, "
	    		+ "stock_nombre_doses_moderna, livraisons_cumul_nombre_total_doses, livraisons_cumul_nombre_doses_pfizer, "
	    		+ "livraisons_cumul_nombre_doses_moderna, total_prises_rendez_vous_semaine, "
	    		+ "prises_rendez_vous_semaine_rang1, prises_rendez_vous_semaine_rang2, stock_ehpad_nombre_doses_pfizer) "
	    		+ "VALUES (:casConfirmes, :deces, :decesEhpad, :reanimation, :hospitalises, :gueris, :date, :nouvellesHospitalisations, "
	    		+ ":nouvellesReanimations, :nouvellesPremieresInjections, :cumulPremieresInjections, "
	    		+ ":stockNombreTotalDoses, :stockNombreDosesPfizer, :stockNombreDosesModerna, "
	    		+ ":livraisonsCumulNombreTotalDoses, :livraisonsCumulNombreDosesPfizer, "
	    		+ ":livraisonsCumulNombreDosesModerna, :totalPrisesRendezVousSemaine, :prisesRendezVousSemaineRang1, "
	    		+ ":prisesRendezVousSemaineRang2, :stockEhpadNombreDosesPfizer)")
	    .dataSource(dataSource)
	    .build();
	}
	
	@Bean
	Job job(JobBuilderFactory jobBuilderFactory,
			@Qualifier("restSituationReelleStep") Step restSituationReelleStep,
			@Qualifier("modelisationsStep") Step modelisationsStep) {
	  return jobBuilderFactory.get("job")
	    .incrementer(new RunIdIncrementer())
	    .flow(restSituationReelleStep)
	    //.next(modelisationsStep)
	    .end()
	    .build();
	}

	@Bean
	Step restSituationReelleStep(ItemReader<SituationReelleDTO> restItemReader, 
			JdbcBatchItemWriter<SituationReelleDTO> restItemWriter,
			StepBuilderFactory stepBuilderFactory) {
	  return stepBuilderFactory.get("restSituationReelleStep")
	    .<SituationReelleDTO, SituationReelleDTO> chunk(1)
	    .reader(restItemReader)
	    .writer(restItemWriter)
	    .build();
	}
	
	@Bean
	JdbcCursorItemReader<SituationReelleDTO> modelisationsItemReader(DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<SituationReelleDTO>()
                .name("modelisationsItemReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM SITUATION_REELLE")
                .rowMapper(new BeanPropertyRowMapper<>(SituationReelleDTO.class))
                .build();
    }
	
	@Bean
	ItemProcessor<SituationReelleDTO, List<Double>> modelisationsItemProcessor() {
		return new ModelisationsItemProcessor();
	}
	
	@Bean
	ItemWriter<List<Double>> modelisationsItemWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
		return new ModelisationsItemWriter(dataSource, jdbcTemplate);
	}
	
	
	@Bean
	Step modelisationsStep(JdbcCursorItemReader<SituationReelleDTO> modelisationsItemReader,
			ItemProcessor<SituationReelleDTO, List<Double>> modelisationsItemProcessor,
			ItemWriter<List<Double>> modelisationsItemWriter,
			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("modelisationsStep")
			    .<SituationReelleDTO, List<Double>> chunk(1)
			    .reader(modelisationsItemReader)
			    .processor(modelisationsItemProcessor)
			    .writer(modelisationsItemWriter)
			    .build();
	}
	
	@Bean
    public PlatformTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
	
}
