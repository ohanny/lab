package fr.icodem.lab.batch11;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import java.io.IOException;
import java.io.Writer;

public class CountryHeaderCallBack implements FlatFileHeaderCallback{
    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write("Code,Name,Continent,Region,SurfaceArea,IndependanceYear,Population,LifeExpectancy");
    }

}
