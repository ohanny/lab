package fr.icodem.lab.batch12;

import fr.icodem.lab.util.ZipManager;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UnzipFileTasklet implements Tasklet {

    private String archiveFile;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            ZipManager zipManager = new ZipManager();
            zipManager.unzipFiles(archiveFile);
        } catch (Exception e) {
            throw new UnexpectedJobExecutionException("Could not extract archive file");
        }

        return RepeatStatus.FINISHED;
    }

    public void setArchiveFile(String archiveFile) {
        this.archiveFile = archiveFile;
    }

}
