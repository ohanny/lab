package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;
import fr.icodem.lab.model.OrderLine;

import javax.inject.Inject;

public class OrderServiceImpl2 implements OrderService {

    @Inject
    private StockService stockService;

    @Override
    public void saveOrder(Order order) {
        // save order in database
        // ...

        // update stock
        //StockService stockService = new StockServiceImpl1();
        for (OrderLine l : order.getLines()) {
            stockService.removeProduct(l.getProduct().getId(), l.getQuantity());
        }
    }
}
