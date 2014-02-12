package fr.icodem.lab.service;

import fr.icodem.lab.model.Order;

public interface Application {
    void saveOrder(Order order);

    short getProductCountInStock(int productId);
}
