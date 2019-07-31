package com.example.fetchshopdata;

public class Item {

    int id;
    String name;
    double price;
    String shortDescription;
    boolean isItemOfTheWeek;
    String imgPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean isItemOfTheWeek() {
        return isItemOfTheWeek;
    }

    public void setItemOfTheWeek(boolean itemOfTheWeek) {
        isItemOfTheWeek = itemOfTheWeek;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String toString(){
        return "ID: " + String.valueOf(id) + "\n" +"Name: "+ name + "\n" +"Price: "+ String.valueOf(price) + "\n" +"Short Description: "+ shortDescription + "\n" +"Image Path: " + imgPath +"\n------------------------------\n";
    }

}
