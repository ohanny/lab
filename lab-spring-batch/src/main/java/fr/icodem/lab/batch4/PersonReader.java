package fr.icodem.lab.batch4;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class PersonReader implements ItemReader<String> {

    private String[] items = {
            "Émile Lampion",
            "Jean Larosière",
            "Alice Avril",
            "Erwan",
            "Swan Laurence",
            "Georges Leroy"
    };

    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < items.length) {
            return items[count++];
        }
        return null;
    }
}
