package fr.icodem.lab.batch2;

import fr.icodem.lab.model.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) throws Exception {
        String name = person.getLastName().toUpperCase();
        person.setLastName(name);
        return person;
    }
}
