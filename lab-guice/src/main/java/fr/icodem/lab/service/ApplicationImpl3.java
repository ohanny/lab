package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

import javax.inject.Inject;

public class ApplicationImpl3 implements Application {
    private OrderService orderService;
    private StockService stockService;

    @Override
    public void saveOrder(Order order) {
        orderService.saveOrder(order);
    }

    @Override
    public short getProductCountInStock(int productId) {
        return stockService.getProductCount(productId);
    }

    // setters
    @Inject
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Inject
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
}
