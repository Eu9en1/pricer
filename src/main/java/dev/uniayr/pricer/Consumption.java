package dev.uniayr.pricer;

public class Consumption {

    private String item;
    private String product;
    private double price;

    public Consumption(String item, String product, double price) {
        this.item = item;
        this.product = product;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
