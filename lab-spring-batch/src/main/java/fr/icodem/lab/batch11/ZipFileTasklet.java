package fr.icodem.lab.batch11;

import fr.icodem.lab.util.ZipManager;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

//@Component("zipFileTasklet") @StepScope
public class ZipFileTasklet implements Tasklet {

    //@Value("#{jobParameters['archive.file']}") // PB CREATION PROXY
    private String archiveFile;

    //@Value("#{jobParameters['countries.file']}")
    private String countriesFile;

    //@Value("#{jobParameters['languages.file']}")
    private String languagesFile;

    //@Value("#{jobParameters['cities.file']}")
    private String citiesFile;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            ZipManager zipManager = new ZipManager();
            zipManager.zipAndRemoveFiles(archiveFile, countriesFile, languagesFile, citiesFile);
        } catch (Exception e) {
            throw new UnexpectedJobExecutionException("Could not create archive file");
        }

        return RepeatStatus.FINISHED;
    }

    public void setArchiveFile(String archiveFile) {
        this.archiveFile = archiveFile;
    }

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
