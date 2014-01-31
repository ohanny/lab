package fr.icodem.eshop.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderBatch {
  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-batch.xml");
    ctx.start();

    JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");

    Job job = (Job) ctx.getBean("importOrders");

    JobParameters parameter = 
        new JobParametersBuilder()
          .addDate("date", new Date())
          .addString("input.file", "C:/envdev/travail/in/personnes.txt")
          .toJobParameters();

    jobLauncher.run(job, parameter);
    
    ctx.close();
  }
}
