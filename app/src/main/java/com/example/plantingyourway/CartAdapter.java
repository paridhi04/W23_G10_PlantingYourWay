package com.example.plantingyourway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private ArrayList<Product> productList;
    private LayoutInflater layoutInflater;

    public CartAdapter(Context context, ArrayList<Product> productList) {
        this.productList = productList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        if (productList == null) {
            return 0;
        }
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.list_item_cart, parent, false);
        } else {
            view = convertView;
        }

        Product product = productList.get(position);

        ImageView imgProduct = view.findViewById(R.id.imgProduct);
        imgProduct.setImageResource(product.getImage());

        TextView txtName = view.findViewById(R.id.txtName);
        txtName.setText(product.getName());

        TextView txtPrice = view.findViewById(R.id.txtPrice);
        txtPrice.setText(product.getPrice());

        TextView txtQuantity = view.findViewById(R.id.txtQuantity);
        txtQuantity.setText("Quantity: " + product.getQuantity());

        return view;
    }
}
