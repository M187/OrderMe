package com.michalhornak.orderapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michalhornak.orderapp.data.Category;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class CategoryDetailFragment extends Fragment {

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    private static final String SELECETD_CATEGORY = "selectedCategory";
    private Category selectedCategory;
    private mViewHolder mViewHolder = new mViewHolder();

    public static CategoryDetailFragment newInstance(Category category) {
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECETD_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
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

        if (selectedCategory == null) {
            ((TextView) view.findViewById(R.id.category_detail_text)).setText("pick category");
        } else {
            ((TextView) view.findViewById(R.id.category_detail_text)).setText(selectedCategory.getName());
        }
        return view;
    }
}
