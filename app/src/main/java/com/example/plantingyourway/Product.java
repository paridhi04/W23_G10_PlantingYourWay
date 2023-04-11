package com.example.plantingyourway;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;


public class Product implements Parcelable {
    private String name;
    private String price;
    private String description;
    private int imageResourceId;
    private int quantity;

    private int image;

    public Product(String name, String price, String description, int imageResourceId,int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.quantity = 0;
    }


    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getImage() {
        return image;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    private Product(Parcel in) {
        name = in.readString();
        price = in.readString();
        description = in.readString();
        imageResourceId = in.readInt();
        quantity = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeInt(imageResourceId);
        dest.writeInt(quantity);
    }
}

