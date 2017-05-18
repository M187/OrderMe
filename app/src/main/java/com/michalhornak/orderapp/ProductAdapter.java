package com.michalhornak.orderapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalhornak.orderapp.data.Category;
import com.michalhornak.orderapp.data.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal.hornak on 5/18/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> mProducts = new ArrayList<>();
    private static ProductClickListener mCLickListener;

    public ProductAdapter(List<Product> categories, ProductClickListener listener) {
        mProducts = categories;
        mCLickListener = listener;
    }

    public interface ProductClickListener {
        void onProductItemClick(Product category);
    }

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        holder.productName.setText(mProducts.get(position).getNAME());
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCLickListener.onProductItemClick(mProducts.get(getAdapterPosition()));
        }
    }

}
