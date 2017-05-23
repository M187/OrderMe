package com.michalhornak.orderapp.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by michal.hornak on 5/19/2017.
 */
public class CustomTextViewBold extends TextView {

    private int typefaceType;

    public CustomTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomTextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context, attrs);
    }

    public CustomTextViewBold(Context context) {
        super(context);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            setTypeface(getTypeFace(), Typeface.BOLD);
        }
    }

    public Typeface getTypeFace() {
        return Typeface.createFromAsset(getContext().getAssets(),"fonts/roboto_regular.ttf");
    }
}
