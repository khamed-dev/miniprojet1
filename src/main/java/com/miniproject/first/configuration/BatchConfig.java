package com.miniproject.first.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.miniproject.first.domain.UserBatch;
 
 
@Configuration
@EnableBatchProcessing
public class BatchConfig 
{
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
     
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
   
  
 
    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }
 
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<UserBatch, UserBatch>chunk(5)
                .reader(reader())
                .processor(process())
                .writer(writer())
                .build();
    }
 
    @Bean
    public FlatFileItemReader < UserBatch > reader() {
        FlatFileItemReader < UserBatch > reader = new FlatFileItemReader < UserBatch > ();
        reader.setResource(new ClassPathResource("data.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }
    
	@Bean 
	public LineMapper<UserBatch> lineMapper(){
	DefaultLineMapper<UserBatch> lineMapper = new DefaultLineMapper<>();
	DelimitedLineTokenizer lineToken = new DelimitedLineTokenizer();
	lineToken.setDelimiter(",");
	lineToken.setStrict(false);
	lineToken.setNames("first","last","gpa","age");
	lineMapper.setLineTokenizer(lineToken);
	BeanWrapperFieldSetMapper<UserBatch> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	fieldSetMapper.setTargetType(UserBatch.class);
	lineMapper.setFieldSetMapper(fieldSetMapper);
	return lineMapper;
}
     
    @Bean
    public ItemWriter<UserBatch> writer() {

            return new UserBatchItemWriter();
    }
    
    @Bean
    public ItemProcessor<UserBatch, UserBatch>  process() {

            return new UserBatchItemProcessor();
    }
    
    
    

}