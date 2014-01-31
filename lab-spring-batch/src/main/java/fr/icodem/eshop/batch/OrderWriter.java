package fr.icodem.eshop.batch;

import java.util.List;

import fr.icodem.lab.model.Order;
import org.springframework.batch.item.ItemWriter;

public class OrderWriter implements ItemWriter<Order> {

  @Override
  public void write(List<? extends Order> orders) throws Exception {
    System.out.println("OrderJdbcWriter.write()");
    for (Order order : orders) {
      System.out.println("Écriture commande : " + order);
    }
  }

}
