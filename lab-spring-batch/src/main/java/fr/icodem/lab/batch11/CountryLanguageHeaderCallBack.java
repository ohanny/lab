package fr.icodem.lab.batch11;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import java.io.IOException;
import java.io.Writer;

public class CountryLanguageHeaderCallBack implements FlatFileHeaderCallback{
    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write("CountryCode,Language,isOfficial,Percentage");
    }

}
