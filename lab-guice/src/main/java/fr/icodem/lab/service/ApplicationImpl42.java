package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

import javax.inject.Inject;
import javax.inject.Named;

public class ApplicationImpl42 implements Application {
    @Inject @Named("Implementation22") private OrderService orderService;
    @Inject private StockService stockService;

    @Override
    public void saveOrder(Order order) {
        orderService.saveOrder(order);
        System.out.println("Implementation 22");
    }

    @Override
    public short getProductCountInStock(int productId) {
        return stockService.getProductCount(productId);
    }
}
