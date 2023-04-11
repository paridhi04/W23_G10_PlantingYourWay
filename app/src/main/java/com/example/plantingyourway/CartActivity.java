package com.example.plantingyourway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.plantingyourway.CartAdapter;
import com.example.plantingyourway.Product;
import java.util.ArrayList;
import android.content.Intent;
public class CartActivity extends AppCompatActivity {

    // Declare the cart as an ArrayList of products
    ArrayList<Product> cart;

    // Declare the ListView and TextView as class variables
    ListView lvCart;
    TextView tvTotalPrice;

    // Declare the CartAdapter as a class variable
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Get the cart from the intent
        cart = new ArrayList<Product>();
        if (getIntent().hasExtra("cart")) {
            cart = getIntent().getParcelableArrayListExtra("cart");
        }


        // Get references to the ListView and TextView
        lvCart = findViewById(R.id.lvCart);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        // Set the adapter for the ListView
        adapter = new CartAdapter(this, cart);
        lvCart.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // Calculate and set the total price

        double totalPrice = 0;
        StringBuilder selectedItems = new StringBuilder();
        for (Product p : cart) {
            String price = p.getPrice().replace("$", "");
            totalPrice += Double.parseDouble(price) * (double) (p.getQuantity());
            selectedItems.append(p.getName()).append(", ");
        }
        String actionBarTitle = selectedItems.toString();
        if (actionBarTitle.length() > 2) {
            actionBarTitle = actionBarTitle.substring(0, actionBarTitle.length() - 2);
        }
        getSupportActionBar().setTitle(actionBarTitle);

        tvTotalPrice.setText("Total Price:" + String.format("%.2f", totalPrice));
        TextView tvSelectedItems = findViewById(R.id.tvSelectedItems);
        tvSelectedItems.setText( selectedItems.toString());
    }

}
