package fr.icodem.lab.batch4;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-batch4.xml");
        ctx.start();

        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");

        Job job = (Job) ctx.getBean("helloPersons");

        JobParameters params = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();
        jobLauncher.run(job, params);

        ctx.close();
    }

}
