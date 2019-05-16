package dev.uniayr.pricer;

public class Tarif {

    public Tarif(String item, double price) {
        this.item = item;
        this.price = price;
    }

    private String item;

    private double price;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
