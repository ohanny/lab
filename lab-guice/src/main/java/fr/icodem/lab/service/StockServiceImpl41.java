package fr.icodem.lab.service;

import fr.icodem.lab.model.Product;
import fr.icodem.lab.model.Stock;
import fr.icodem.lab.model.StockItem;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class StockServiceImpl41 implements StockService {

    private Stock stock;

    @Inject // simulate post construct => OK when no other injection
    public void init() {
        Map<Integer, StockItem> map = new HashMap<>();

        Product p = new Product(1 , "Astérix en Corse");
        StockItem item = new StockItem(1, p, (short)5);
        map.put(p.getId(), item);

        this.stock = new Stock(map);
    }

    @Override
    public void removeProduct(int productId, short quantity) {

        stock.removeProduct(productId, quantity);

        System.out.printf("Removed product %s (%d item(s)) ", productId, quantity);
    }

    @Override
    public short getProductCount(int productId) {
        return stock.getProductCount(productId);
    }

}
