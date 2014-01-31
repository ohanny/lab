package fr.icodem.lab.batch3;

import fr.icodem.lab.model.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<String, Person> {
    @Override
    public Person process(String str) throws Exception {
        String[] tab = str.split(" ");
        Person p = new Person(tab[0], tab[1].toUpperCase());
        return p;
    }
}
