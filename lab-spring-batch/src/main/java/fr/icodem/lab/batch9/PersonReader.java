package fr.icodem.lab.batch9;

import fr.icodem.lab.model.Person;
import org.springframework.batch.item.*;

public class PersonReader implements ItemReader<Person>, ItemStream {

    private Person[] items = {
            new Person("Émile", "Lampion"),
            new Person("Jean", "Larosière"),
            new Person("Alice", null),// new Person("Alice", "Avril") // last name = null, bad entry : exception in processor
            new Person("Swan", "Laurence"),
            new Person("Georges", "Leroy")
    };

    private int currentIndex = 0;

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (currentIndex < items.length) {
            return items[currentIndex++];
        }
        return null;
    }

    // item stream methods
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("Open item stream => current index = " +currentIndex);
        if(executionContext.containsKey("current.index")){
            currentIndex = new Long(executionContext.getLong("current.index")).intValue();
        }
        else{
            currentIndex = 0;
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("Update item stream with index = " + currentIndex);
        executionContext.putLong("current.index", new Long(currentIndex).longValue());
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("Close item stream => current index = " +currentIndex);
    }
}
