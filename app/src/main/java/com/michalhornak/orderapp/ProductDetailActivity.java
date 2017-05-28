package com.michalhornak.orderapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.michalhornak.orderapp.data.Product;

import java.util.ArrayList;

import static com.michalhornak.orderapp.MainActivity.ORDERED_PRODUCT_LIST;
import static com.michalhornak.orderapp.ProductDetailFragment.currentProduct;

/**
 * Created by michal.hornak on 5/22/2017.
 */

public class ProductDetailActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.productList = getIntent().getParcelableArrayListExtra("products");
        setContentView(R.layout.product_detail_viewpager);

        //Todo - do this based on aspect ratios
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 1000;
        params.width = 1800;

        this.getWindow().setAttributes(params);

        mPager = (ViewPager) findViewById(R.id.category_detail_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, (ViewPager.PageTransformer) mPagerAdapter);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(
                this.productList.indexOf(getIntent().getParcelableExtra("productClicked"))
        );
    }

    public void addToBasket(View view){
        ORDERED_PRODUCT_LIST.add(currentProduct);
        Toast.makeText(this, "Product added to basket.", Toast.LENGTH_SHORT);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter implements ViewPager.PageTransformer{

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ProductDetailFragment.newInstance(productList.get(position));
        }

        @Override
        public int getCount() {
            return productList.size();
        }

        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
