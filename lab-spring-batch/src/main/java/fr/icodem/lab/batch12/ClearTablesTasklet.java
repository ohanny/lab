package fr.icodem.lab.batch12;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClearTablesTasklet implements Tasklet {

    private DataSource dataSource;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("delete from city")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = connection.prepareStatement("delete from country_language")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = connection.prepareStatement("delete from country")) {
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new UnexpectedJobExecutionException("Could not clear tables");
        }

        return RepeatStatus.FINISHED;
    }

    // getters and setters
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
