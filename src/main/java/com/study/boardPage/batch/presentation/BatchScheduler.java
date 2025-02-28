package com.study.boardPage.batch.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class BatchScheduler{
    private final JobLauncher jobLauncher;
    private final Job inactiveUserJob;

    @Scheduled(cron ="0 42 16 * * ?")
    public void runInactiveUserJob() throws JobExecutionException{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(inactiveUserJob, jobParameters);

    }
}
