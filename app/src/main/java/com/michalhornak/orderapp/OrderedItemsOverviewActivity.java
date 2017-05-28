package com.michalhornak.orderapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.michalhornak.orderapp.data.Product;

/**
 * Created by michal.hornak on 5/26/2017.
 */

public class OrderedItemsOverviewActivity extends Activity implements OrderItemAdapter.OrderItemClickListener {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order_summary);

        //Todo - do this based on aspect ratios
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 1000;
        params.width = 1800;

        this.getWindow().setAttributes(params);

        mRecyclerView = (RecyclerView) findViewById(R.id.ordered_items_list);
        try {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(new OrderItemAdapter(this));
        } catch (NullPointerException e){
            //TODO: Handle no ordered product state.
            Toast.makeText(this, "No ordered products! Handle this state", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onProductItemClick(Product category) {
        Toast.makeText(this, "Clicked category " + category.getNAME(), Toast.LENGTH_SHORT).show();
    }
}
