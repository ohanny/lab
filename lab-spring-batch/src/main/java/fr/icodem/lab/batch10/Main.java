package fr.icodem.lab.batch10;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-batch10.xml");
        ctx.start();

        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");

        Job job = (Job) ctx.getBean("extractWorld");

        JobParameters params = new JobParametersBuilder()
                .addDate("date", new Date())
                .addString("cities.file", "lab-spring-batch/data/cities.csv")
                .toJobParameters();
        jobLauncher.run(job, params);

        ctx.close();
    }

}
