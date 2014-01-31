package fr.icodem.lab.batch5;

import fr.icodem.lab.model.Person;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PersonFieldSetMapper implements FieldSetMapper<Person> {

    @Override
    public Person mapFieldSet(FieldSet fieldSet) throws BindException {
        Person p = new Person();
        p.setFirstName(fieldSet.readString(0));
        p.setLastName(fieldSet.readString(1));
        p.setAge(fieldSet.readInt(2));
        return p;
    }
}
