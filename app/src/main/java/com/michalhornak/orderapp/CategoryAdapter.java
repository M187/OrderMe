package com.michalhornak.orderapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michalhornak.orderapp.data.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<Category> mCategories = new ArrayList<>();
    private static CategoryClickListener mCLickListener;

    public CategoryAdapter(List<Category> categories, CategoryClickListener listener){
        mCategories = categories;
        mCLickListener = listener;
    }

    public interface CategoryClickListener{
        void onCategoryItemClick(Category category);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.categoryName.setText(mCategories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView categoryName;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryName = (TextView) itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCLickListener.onCategoryItemClick(mCategories.get(getAdapterPosition()));
        }
    }

}
