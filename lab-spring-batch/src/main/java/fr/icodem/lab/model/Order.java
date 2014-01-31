package fr.icodem.lab.model;

import java.util.Date;
import java.util.List;

public class Order {
    private long id;
    private Date date;
    private String trackingNumber;
    private OrderStatus status;

    private List<OrderLine> lines;

    @Override
    public String toString() {
        return "Order [id=" + id + ", date=" + date + ", trackingNumber="
                + trackingNumber + ", status=" + status + ", lines=" + lines + "]";
    }

    // getters et setters
    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
