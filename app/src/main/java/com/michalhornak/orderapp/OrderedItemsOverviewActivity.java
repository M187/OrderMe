package com.michalhornak.orderapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by michal.hornak on 5/26/2017.
 */

public class OrderedItemsOverviewActivity extends Activity {

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
    }
}
