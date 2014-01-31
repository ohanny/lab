package fr.icodem.lab.model;

public class OrderLine {
    private long id;
    private int quantity;
    private int productId;

    @Override
    public String toString() {
        return "OrderLine [id=" + id + ", quantity=" + quantity + ", productId="
                + productId + "]";
    }

    // getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
