package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

import javax.inject.Inject;

public class ApplicationImpl41 implements Application {
    @Inject @Implementation41
    private OrderService orderService;
    @Inject private StockService stockService;

    @Override
    public void saveOrder(Order order) {
        orderService.saveOrder(order);
        System.out.println("Implementation 21");
    }

    @Override
    public short getProductCountInStock(int productId) {
        return stockService.getProductCount(productId);
    }
}
