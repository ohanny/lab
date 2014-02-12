package fr.icodem.lab.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private Customer customer;
    private List<OrderLine> lines;

    public void addLine(Product product, short quantity) {
        if (lines == null) lines = new ArrayList<>();

        OrderLine line = new OrderLine();
        line.setProduct(product);
        line.setQuantity(quantity);
        lines.add(line);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", lines=" + lines +
                '}';
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }
}
