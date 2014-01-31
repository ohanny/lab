package fr.icodem.lab.batch12;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteFileTasklet implements Tasklet {

    private String countriesFile;
    private String languagesFile;
    private String citiesFile;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            Files.deleteIfExists(Paths.get(countriesFile));
            Files.deleteIfExists(Paths.get(languagesFile));
            Files.deleteIfExists(Paths.get(citiesFile));
        } catch (Exception e) {
            throw new UnexpectedJobExecutionException("Could not delete extracted files");
        }

        return RepeatStatus.FINISHED;
    }

    // getters and setters
    public void setCountriesFile(String countriesFile) {
        this.countriesFile = countriesFile;
    }
    public void setLanguagesFile(String languagesFile) {
        this.languagesFile = languagesFile;
    }
    public void setCitiesFile(String citiesFile) {
        this.citiesFile = citiesFile;
    }
}
