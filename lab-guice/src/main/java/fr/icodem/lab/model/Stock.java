package fr.icodem.lab.model;

import java.util.Map;

public class Stock {
    private Map<Integer, StockItem> idProductToStockItem;

    public Stock(Map<Integer, StockItem> idProductToStockItem) {
        this.idProductToStockItem = idProductToStockItem;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "idProductToStockItem=" + idProductToStockItem +
                '}';
    }

    public short getProductCount(int productId) {
        StockItem item = idProductToStockItem.get(productId);
        return item.getCount();
    }

    public StockItem removeProduct(int productId, short quantity) {
        StockItem item = idProductToStockItem.get(productId);
        item.remove(quantity);
        return item;
    }


}
