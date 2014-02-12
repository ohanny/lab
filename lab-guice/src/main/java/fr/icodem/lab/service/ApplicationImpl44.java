package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

import javax.inject.Inject;

public class ApplicationImpl44 implements Application {
    @Inject private OrderServiceImpl44 orderService;
    @Inject private StockServiceImpl44 stockService;

    @Override
    public void saveOrder(Order order) {
        orderService.saveOrder(order);
    }

    @Override
    public short getProductCountInStock(int productId) {
        return stockService.getProductCount(productId);
    }
}
