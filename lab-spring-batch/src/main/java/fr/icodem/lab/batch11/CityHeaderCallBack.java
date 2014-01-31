package fr.icodem.lab.batch11;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import java.io.IOException;
import java.io.Writer;

public class CityHeaderCallBack implements FlatFileHeaderCallback{
    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write("ID,Name,CountryCode,District,Population");
    }
}
