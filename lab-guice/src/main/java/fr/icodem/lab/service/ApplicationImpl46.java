package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

import javax.inject.Inject;

public class ApplicationImpl46 implements Application {
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
