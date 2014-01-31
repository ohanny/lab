package fr.icodem.lab.batch4;

import fr.icodem.lab.model.Person;
import org.springframework.batch.item.ItemProcessor;

// filtering person with no name
public class PersonProcessor implements ItemProcessor<String, Person> {
    @Override
    public Person process(String str) throws Exception {
        String[] tab = str.split(" ");
        if (tab.length < 2) return null;
        Person p = new Person(tab[0], tab[1].toUpperCase());
        return p;
    }
}

