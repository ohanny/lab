package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

import javax.inject.Inject;

public class ApplicationImpl45 implements Application {
    private OrderService orderService;
    private StockService stockService;

    public ApplicationImpl45(OrderService orderService, StockService stockService) {
        this.orderService = orderService;
        this.stockService = stockService;
    }

    @Override
    public void saveOrder(Order order) {
        orderService.saveOrder(order);
    }

    @Override
    public short getProductCountInStock(int productId) {
        return stockService.getProductCount(productId);
    }
}
