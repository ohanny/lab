package fr.icodem.lab.model;

public class StockItem {
    private int id;
    private Product product;
    private short count;

    public StockItem() {
    }

    public StockItem(int id, Product product, short count) {
        this.id = id;
        this.product = product;
        this.count = count;
    }

    public void remove(short quantity) {
        if (count - quantity < 0)
            throw new IllegalArgumentException("Cannot remove more products than " + count);
        count = (short)(count - quantity);
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                '}';
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }

}
