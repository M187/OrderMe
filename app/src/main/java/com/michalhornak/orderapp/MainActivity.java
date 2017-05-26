package com.michalhornak.orderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;

import com.michalhornak.orderapp.data.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    public static boolean IS_TWO_PANE = false;

    //Actual order list to store all ordered/favourite products
    public static List<Product> ORDERED_PRODUCT_LIST = new ArrayList<>();

    {
        //This is to create dummy data for order. Can be removed later
        ORDERED_PRODUCT_LIST.add(new Product("1", "1", "Spagethi monster with ketchup and cheese", "15,00"));
        ORDERED_PRODUCT_LIST.add(new Product("11", "11", "Coca cola", "5,00"));
        ORDERED_PRODUCT_LIST.add(new Product("2", "1", "Pancakes with chocolate and fruits.", "10,00"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.IS_TWO_PANE = (findViewById(R.id.fragment_details) != null) ? true : false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void showLanguageMenu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.language_menu, popup.getMenu());
        popup.show();
    }

    public void showOrderedItems(View v){
        Intent temp = new Intent(this, OrderedItemsOverviewActivity.class);
        startActivity(temp);
    }
}
