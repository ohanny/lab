package fr.icodem.lab.service;

import fr.icodem.lab.model.Product;
import fr.icodem.lab.model.Stock;
import fr.icodem.lab.model.StockItem;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.util.HashMap;
import java.util.Map;

public class StockProvider48 implements Provider<Stock> {

    @Inject @Named("Initial Quantity") private short initialQuantity;

    @Override
    public Stock get() {
        Map<Integer, StockItem> map = new HashMap<>();

        Product p = new Product(1 , "Astérix en Corse");
        StockItem item = new StockItem(1, p, initialQuantity);
        map.put(p.getId(), item);
        Stock stock = new Stock(map);

        return stock;
    }
}
