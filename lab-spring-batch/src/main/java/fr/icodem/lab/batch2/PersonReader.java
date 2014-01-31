package fr.icodem.lab.batch2;

import fr.icodem.lab.model.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class PersonReader implements ItemReader<Person> {

    private Person[] items = {
            new Person("Émile", "Lampion"),
            new Person("Jean", "Larosière"),
            new Person("Alice", "Avril"),
            new Person("Swan", "Laurence"),
            new Person("Georges", "Leroy")
    };

    private int count = 0;

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < items.length) {
            return items[count++];
        }
        return null;
    }
}
