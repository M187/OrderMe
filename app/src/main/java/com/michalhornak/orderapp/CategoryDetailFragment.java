package com.michalhornak.orderapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michalhornak.orderapp.data.Category;
import com.michalhornak.orderapp.data.Product;
import com.michalhornak.persistence.ItemsContract;

import java.util.ArrayList;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class CategoryDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, ProductAdapter.ProductClickListener{

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    private ProgressDialog dialog;
    private static final String SELECETD_CATEGORY = "selectedCategory";
    private Category selectedCategory;
    private mViewHolder mViewHolder = new mViewHolder();
    private RecyclerView mRecyclerView;

    private final int PRODUCTS_LOADER_ID = 2;
    private static final String[] categoriesProjection = {
            ItemsContract.ProductEntry.COLUMN_ID,
            ItemsContract.ProductEntry.COLUMN_CATEGORY_ID,
            ItemsContract.ProductEntry.COLUMN_PRODUCT_NAME,
            ItemsContract.ProductEntry.COLUMN_PRODUCT_PRICE
    };

    public static CategoryDetailFragment newInstance(Category category) {
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECETD_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(
                this.getActivity(),
                ItemsContract.CategoryEntry.CONTENT_URI.buildUpon().appendPath("products").build(),
                categoriesProjection,
                ItemsContract.ProductEntry.COLUMN_CATEGORY_ID + "=" + getSelectedCategory().getID(),
                null,
                null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        //todo parse cursor for data
        ArrayList<Product> temp = new ArrayList<>();

        while (data.moveToNext()) {
            temp.add(new Product(
                    data.getString(0),
                    data.getString(1),
                    data.getString(2),
                    data.getString(3)));
        }

        mRecyclerView.setAdapter(new ProductAdapter(temp, this));
        dialog.dismiss();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onProductItemClick(Product category) {
        Intent temp = new Intent(getActivity(), ProductActivity.class);
        startActivity(temp);
    }

    private class mViewHolder {
        LinearLayout mainLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.selectedCategory = getArguments().getParcelable(SELECETD_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout view;
        if (mViewHolder.mainLayout == null) {
            view = (LinearLayout) inflater.inflate(R.layout.category_detail_layout, container, false);
            mViewHolder.mainLayout = view;
        } else {
            view = mViewHolder.mainLayout;
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_list);

        getLoaderManager().initLoader(PRODUCTS_LOADER_ID, null, this);
        this.dialog = new ProgressDialog(this.getContext());
        dialog.setMessage("Waiting to fetch data");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        return view;
    }
}
