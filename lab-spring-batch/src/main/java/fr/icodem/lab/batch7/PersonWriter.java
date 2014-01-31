package fr.icodem.lab.batch7;

import fr.icodem.lab.model.Person;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PersonWriter implements ItemWriter<Person> {
    @Override
    public void write(List<? extends Person> persons) throws Exception {
        System.out.println("\r\nWriting chunk of persons :");
        for (Person p :persons) {
            System.out.println("\t Hello " + p + " !");
        }
    }
}
