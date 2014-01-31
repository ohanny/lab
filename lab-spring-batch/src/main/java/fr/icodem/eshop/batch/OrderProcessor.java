package fr.icodem.eshop.batch;

import fr.icodem.lab.model.Order;
import org.springframework.batch.item.ItemProcessor;

// règles de gestion du batch
public class OrderProcessor implements ItemProcessor<Order, Order> {

  @Override
  public Order process(Order order) throws Exception {
    System.out.println("OrderProcessor.process => " + order);
    
    return order;
  }

}
