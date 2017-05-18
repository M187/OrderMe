package com.michalhornak.orderapp;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;

import com.michalhornak.orderapp.data.Category;

import java.util.ArrayList;


/**
 * Created by michal.hornak on 3/10/2017.
 */

public class CategoryDetailCollectionFragment extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ProgressDialog dialog;
    CategoryDetailsCollectionPagerAdapter mCategoryDetailsCollectionPagerAdapter;
    ViewPager mViewPager;
    private static final String SELECETD_CATEGORY = "selectedCategory";
    private Cursor mCursor;

    public static CategoryDetailCollectionFragment newInstance(Category category) {
        CategoryDetailCollectionFragment fragment = new CategoryDetailCollectionFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECETD_CATEGORY, category);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_detail_fragment);

        mViewPager = (ViewPager) findViewById(R.id.category_detail_pager);
//        this.dialog = new ProgressDialog(this.getContext());
//        dialog.setMessage("Waiting to fetch data");
//        dialog.setCancelable(false);
//        dialog.setInverseBackgroundForced(false);
//        dialog.show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //this.dialog.hide();
        mCursor = data;

        mCategoryDetailsCollectionPagerAdapter = new CategoryDetailsCollectionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mCategoryDetailsCollectionPagerAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    // Since this is an object collection, use a FragmentStatePagerAdapter,
    // and NOT a FragmentPagerAdapter.
    public class CategoryDetailsCollectionPagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<CategoryDetailFragment> mCategories;

        public CategoryDetailsCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
            CategoryDetailFragment fragment = new CategoryDetailFragment().newInstance(null);
        }

        @Override
        public Fragment getItem(int i) {
            return mCategories.get(i);
        }

        @Override
        public int getCount() {
            return mCategories.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }
}
