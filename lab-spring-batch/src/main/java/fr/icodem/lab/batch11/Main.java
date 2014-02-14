package fr.icodem.lab.batch11;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-batch11.xml");
        ctx.start();

        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");

        Job job = (Job) ctx.getBean("extractWorld");

        JobParameters params = new JobParametersBuilder()
                .addDate("date", new Date())
                .addString("archive.file", "lab-spring-batch/data/output/world.zip", false)
                .addString("countries.file", "lab-spring-batch/data/output/countries.csv", false)
                .addString("languages.file", "lab-spring-batch/data/output/languages.csv", false)
                .addString("cities.file", "lab-spring-batch/data/output/cities.csv", false)
                .toJobParameters();
        jobLauncher.run(job, params);

        ctx.close();
    }

}
