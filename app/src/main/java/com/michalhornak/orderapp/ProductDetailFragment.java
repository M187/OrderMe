package com.michalhornak.orderapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michalhornak.orderapp.data.Product;

/**
 * Created by michal.hornak on 5/26/2017.
 */

public class ProductDetailFragment extends Fragment {

    public static ProductDetailFragment newInstance(Product product){
        ProductDetailFragment f = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("product", product);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.product_detail_layout, container, false);

        Product product = getArguments().getParcelable("product");

        ((TextView)rootView.findViewById(R.id.product_name)).setText(product.getNAME());
        ((TextView)rootView.findViewById(R.id.product_price)).setText(product.getPRICE());

        return rootView;
    }

}
