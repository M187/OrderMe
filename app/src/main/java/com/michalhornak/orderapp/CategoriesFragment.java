package com.michalhornak.orderapp;

import android.support.v4.app.LoaderManager;
import android.app.ProgressDialog;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.michalhornak.orderapp.data.Category;

import java.util.ArrayList;

import static com.michalhornak.orderapp.MainActivity.IS_TWO_PANE;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoriesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, CategoryAdapter.CategoryClickListener {

    private ProgressDialog dialog;
    private RecyclerView mRecyclerView;
    private final int CATEGORY_LOADER_ID = 1;

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_category_list);

        //getLoaderManager().initLoader(CATEGORY_LOADER_ID, null, this);
//        this.dialog = new ProgressDialog(this.getContext());
//        dialog.setMessage("Waiting to fetch data");
//        dialog.setCancelable(false);
//        dialog.setInverseBackgroundForced(false);
//        dialog.show();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        //todo parse cursor for data
        ArrayList<Category> temp = new ArrayList<>();
        temp.add(new Category("beer"));
        temp.add(new Category("vine"));
        temp.add(new Category("shots"));
        temp.add(new Category("shots2"));
        temp.add(new Category("shots3"));
        temp.add(new Category("shots4"));
        temp.add(new Category("shots5"));
        temp.add(new Category("shots6"));

        mRecyclerView.setAdapter(new CategoryAdapter(temp, this));

        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        //todo parse cursor for data
        ArrayList<Category> temp = new ArrayList<>();

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
                    .replace(R.id.fragment_details, CategoryDetailFragment.newInstance(category), "categoryDetailFragment")
                    .commit();
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, CategoryDetailFragment.newInstance(category), "categoryDetailFragment")
                    .addToBackStack(null)
                    .commit();
        }
        Log.d("fragmentCreation", "Click ListItem " + category.getName());
    }
}
