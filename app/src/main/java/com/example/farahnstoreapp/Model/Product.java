package com.example.farahnstoreapp.Model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("icon")
    private String icon;
    @SerializedName("cat_product")
    private String cat;
    @SerializedName("description")
    private String description;
    @SerializedName("qty")
    private String qty;
    @SerializedName("idbasket")
    private String idbasket;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getIdbasket() {
        return idbasket;
    }

    public void setIdbasket(String idbasket) {
        this.idbasket = idbasket;
    }
}
