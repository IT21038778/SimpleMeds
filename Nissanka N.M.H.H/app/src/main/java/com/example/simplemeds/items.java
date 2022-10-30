package com.example.simplemeds;

public class items {
    private String itemCode;
    private String name;
    private String quantity;
    private String mfd;
    private String expd;
    private String price;
    private long itemId;

    public items(){
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {

        this.itemCode = itemCode;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getQuantity() {

        return quantity;
    }

    public void setQuantity(String quantity) {

        this.quantity = quantity;
    }

    public String getMfd() {

        return mfd;
    }

    public void setMfd(String mfd) {

        this.mfd = mfd;
    }

    public String getExpd() {

        return expd;
    }

    public void setExpd(String expd) {

        this.expd = expd;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {

        this.price = price;
    }

    public long getItemId() {

        return itemId;
    }

    public void setItemId(long itemId) {

        this.itemId = itemId;
    }
}
