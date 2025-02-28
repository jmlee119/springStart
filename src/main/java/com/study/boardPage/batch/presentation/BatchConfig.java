package com.study.boardPage.batch.presentation;

import com.study.boardPage.batch.application.InactiveUserProcessor;
import com.study.boardPage.batch.infrastructure.InactiveUserItemReader;
import com.study.boardPage.batch.infrastructure.InactiveUserItemWriter;
import com.study.boardPage.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final InactiveUserItemReader inactiveUserItemReader;
    private final InactiveUserItemWriter inactiveUserItemWriter;
    private final InactiveUserProcessor inactiveUserProcessor;

    @Bean
    public Job inactiveUserJob() {
        return new JobBuilder("inactiveUserJob", jobRepository)
                .start(inactiveUserStep())
                .build();
        
    }

    @Bean
    public Step inactiveUserStep() {
        return new StepBuilder("inactiveUserStep", jobRepository)
                .<Users, Users>chunk(10,transactionManager)
                .reader(inactiveUserItemReader)
                .processor(inactiveUserProcessor)
                .writer(inactiveUserItemWriter)
                .build();
    }
}
