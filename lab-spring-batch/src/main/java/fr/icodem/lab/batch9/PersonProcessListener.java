package fr.icodem.lab.batch9;

import fr.icodem.lab.model.Person;
import org.springframework.batch.core.ItemProcessListener;

public class PersonProcessListener implements ItemProcessListener<Person, Person> {
    @Override
    public void beforeProcess(Person p) {

    }

    @Override
    public void afterProcess(Person p1, Person p2) {

    }

    @Override
    public void onProcessError(Person p, Exception e) {
        System.out.println("LISTENER ERROR => " + p);
        System.out.println("\tCHANGE THIS VALUE IN PERSON READER");
    }
}
