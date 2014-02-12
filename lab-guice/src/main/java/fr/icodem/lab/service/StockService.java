package fr.icodem.lab.service;

public interface StockService {
    void removeProduct(int productId, short quantity);
    public short getProductCount(int productId);
}
