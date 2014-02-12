package fr.icodem.lab.service;

import fr.icodem.lab.model.Product;
import fr.icodem.lab.model.Stock;
import fr.icodem.lab.model.StockItem;

import javax.inject.Provider;
import java.util.HashMap;
import java.util.Map;

public class StockProvider47 implements Provider<Stock> {

    @Override
    public Stock get() {
        Map<Integer, StockItem> map = new HashMap<>();

        Product p = new Product(1 , "Astérix en Corse");
        StockItem item = new StockItem(1, p, (short)7);
        map.put(p.getId(), item);
        Stock stock = new Stock(map);

        return stock;
    }
}
