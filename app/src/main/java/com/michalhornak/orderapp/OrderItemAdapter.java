package com.michalhornak.orderapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalhornak.orderapp.data.Product;

import static com.michalhornak.orderapp.MainActivity.ORDERED_PRODUCT_LIST;

/**
 * Created by michal.hornak on 5/28/2017.
 */

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private static OrderItemAdapter.OrderItemClickListener mCLickListener;

    public OrderItemAdapter(OrderItemAdapter.OrderItemClickListener listener) {
        mCLickListener = listener;
    }

    public interface OrderItemClickListener {
        void onProductItemClick(Product category);
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false);
        return new OrderItemAdapter.OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder holder, int position) {
        holder.productName.setText(ORDERED_PRODUCT_LIST.get(position).getNAME());
    }

    @Override
    public int getItemCount() {
        return ORDERED_PRODUCT_LIST.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName;
        TextView productPrice;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCLickListener.onProductItemClick(ORDERED_PRODUCT_LIST.get(getAdapterPosition()));
        }
    }
}
