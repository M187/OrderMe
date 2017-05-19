package com.michalhornak.orderapp;

import android.app.ProgressDialog;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.michalhornak.orderapp.data.Category;
import com.michalhornak.persistence.ItemsContract;

import java.util.ArrayList;

import static com.michalhornak.orderapp.MainActivity.IS_TWO_PANE;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoriesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, CategoryAdapter.CategoryClickListener {

    private ProgressDialog dialog;
    private RecyclerView mRecyclerView;

    private final int CATEGORY_LOADER_ID = 1;
    private static final String[] categoriesProjection = {
            ItemsContract.CategoryEntry.COLUMN_ID,
            ItemsContract.CategoryEntry.COLUMN_CATEGORY_NAME,
    };

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_category_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        getLoaderManager().initLoader(CATEGORY_LOADER_ID, null, this);
        this.dialog = new ProgressDialog(this.getContext());
        dialog.setMessage("Waiting to fetch data");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(
                this.getActivity(),
                ItemsContract.CategoryEntry.CONTENT_URI.buildUpon().appendPath("category").build(),
                categoriesProjection,
                null,
                null,
                null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        //todo parse cursor for data
        ArrayList<Category> temp = new ArrayList<>();

        while (data.moveToNext()) {
            temp.add(new Category(data.getString(1), data.getString(0)));
        }

        mRecyclerView.setAdapter(new CategoryAdapter(temp, this));
        dialog.dismiss();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    public void onCategoryItemClick(Category category) {

        if (IS_TWO_PANE) {
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragment_details, CategoryDetailFragment.newInstance(category), "categoryDetailFragment")
                    .commit();
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragment, CategoryDetailFragment.newInstance(category), "categoryDetailFragment")
                    .addToBackStack(null)
                    .commit();
        }
        Log.d("fragmentCreation", "Click ListItem " + category.getName());
    }
}
