package fr.icodem.lab.service;

import fr.icodem.lab.model.Stock;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StockServiceImpl46 implements StockService {

    @Inject private Stock stock;

    @Override
    public void removeProduct(int productId, short quantity) {

        stock.removeProduct(productId, quantity);

        System.out.println(this);
        System.out.printf("Removed product %s (%d item(s)) ", productId, quantity);
    }

    @Override
    public short getProductCount(int productId) {
        return stock.getProductCount(productId);
    }

}
