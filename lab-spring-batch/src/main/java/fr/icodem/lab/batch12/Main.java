package fr.icodem.lab.batch12;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-batch12.xml");
        ctx.start();

        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");

        Job job = (Job) ctx.getBean("importWorld");

        JobParameters params = new JobParametersBuilder()
                //.addDate("date", new Date())
                .addLong("ID", 2l)
                .addString("archive.file", "lab-spring-batch/data/input/world-ko.zip", false)
                .addString("countries.file", "lab-spring-batch/data/input/countries.csv", false)
                .addString("languages.file", "lab-spring-batch/data/input/languages.csv", false)
                .addString("cities.file", "lab-spring-batch/data/input/cities.csv", false)
                .toJobParameters();
        jobLauncher.run(job, params);

        ctx.close();
    }

}
