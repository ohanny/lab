package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

import javax.inject.Inject;
import javax.inject.Named;

public class ApplicationImpl43 implements Application {
    @Inject private OrderService orderService;
    @Inject private StockService stockService;

    @Override
    public void saveOrder(Order order) {
        orderService.saveOrder(order);
    }

    @Override
    public short getProductCountInStock(int productId) {
        return stockService.getProductCount(productId);
    }
}
