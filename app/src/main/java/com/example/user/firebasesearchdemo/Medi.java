package com.example.user.firebasesearchdemo;

/**
 * Created by user on 3/9/2018.
 */

public class Medi {

    private String Name, Content, Brand, Image, Description, Precautions,Side_effects;
    private Double Price;

    public Medi()
    {}


    public String getName() {
        return Name;
    }

    public Double getPrice() {
        return Price;
    }

    public String getContent() {
        return Content;
    }

    public String getImage() {
        return Image;
    }

    public String getBrand() {
        return Brand;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrecautions() {
        return Precautions;
    }

    public String getSide_effects() {
        return Side_effects;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setPrecautions(String Precautions) {
        this.Precautions = Precautions;
    }

    public void setSide_effects(String Side_effects) {
        this.Side_effects = Side_effects;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public Medi(String Name, Double Price, String Content, String Brand, String Description, String Precautions, String Side_effects) {
        this.Name = Name;
        this.Price = Price;
        this.Content = Content;
        this.Brand = Brand;
        this.Image = Image;
        this.Description = Description;
        this.Precautions = Precautions;
        this.Side_effects = Side_effects;
    }
}
