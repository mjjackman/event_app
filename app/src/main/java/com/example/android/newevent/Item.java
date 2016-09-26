package com.example.android.newevent;

import java.text.DecimalFormat;

/**
 * Created by Jackman on 11/07/2016.
 */
public class Item {

    private String itemName;
    private double itemPrice;
    private int quantity;
    final DecimalFormat df = new DecimalFormat("#.00");


    public Item(String name, double price, int quant) {
        this.setItemName(name);
        this.setItemPrice(price);
        this.setQuantity(quant);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return this.getQuantity() + " x " +this.getItemName() + "  £"+ (df.format(this.getItemPrice()));
    }
}