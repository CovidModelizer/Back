package com.inf1.app.batch;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BatchScheduler {

    private JobLauncher jobLauncher;
    private Job recupDonneesQuotidiennesJob;

    @Scheduled(cron = "${batch-data.cron}", zone = "${batch.zone}")
    public void schedule() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        jobLauncher.run(recupDonneesQuotidiennesJob,
                new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());
    }
}