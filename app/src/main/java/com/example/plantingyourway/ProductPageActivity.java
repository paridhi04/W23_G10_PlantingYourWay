package com.example.plantingyourway;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.ArrayList;
import com.example.plantingyourway.Product;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
public class ProductPageActivity extends AppCompatActivity {


    // Declare the cart as an ArrayList of products
    ArrayList<Product> cart = new ArrayList<>();

    // Declare the products as class variables
    Product product1;
    Product product2;
    Product product3;

    // Declare the buttons as class variables
    Button btnAddToCart1;
    Button btnAddToCart2;
    Button btnAddToCart3;
    Button btnViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        // Instantiate the products
        product1 = new Product("Fresh Plant", "$8.99", "Product Description", R.drawable.img_4, 0);
        product2 = new Product("Fresh Plant", "$9.99", "Product Description", R.drawable.img_4,0);
        product3 = new Product("Fresh Plant", "$10.99", "Product Description", R.drawable.img_4,0);

        // Get references to the buttons
        btnAddToCart1 = findViewById(R.id.btnAddToCart1);
        btnAddToCart2 = findViewById(R.id.btnAddToCart2);
        btnAddToCart3 = findViewById(R.id.btnAddToCart3);
        btnViewCart = findViewById(R.id.viewCartButton);

        // Set click listeners for the buttons
        btnAddToCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = product1;
                int index = cart.indexOf(product);
                if (index != -1) {
                    // Add the selected product to the cart
                    product = cart.get(index);
                    product.setQuantity(product.getQuantity() + 1);
                } else {
                    product.setQuantity(1);
                    cart.add(product);
                }
                Toast.makeText(ProductPageActivity.this, "Product added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToCart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = product2;
                int index = cart.indexOf(product);
                if (index != -1) {
                    // Add the selected product to the cart
                    product = cart.get(index);
                    product.setQuantity(product.getQuantity() + 1);
                } else {
                    product.setQuantity(1);
                    cart.add(product2);
                    cart.add(product);
                }
                Toast.makeText(ProductPageActivity.this, "Product added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToCart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = product3;
                int index = cart.indexOf(product);
                if (index != -1) {
                    // Add the selected product to the cart
                    product = cart.get(index);
                    product.setQuantity(product.getQuantity() + 1);
                } else {
                    product.setQuantity(1);
                    cart.add(product3);
                    cart.add(product);
                }
                Toast.makeText(ProductPageActivity.this, "Product added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the CartActivity
                Intent intent = new Intent(ProductPageActivity.this, CartActivity.class);
                intent.putExtra("cart", cart);
                startActivity(intent);
            }
        });
    }

}