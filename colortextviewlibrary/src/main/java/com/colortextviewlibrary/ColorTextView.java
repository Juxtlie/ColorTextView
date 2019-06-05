package com.colortextviewlibrary;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.TypedValue;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * Created by Ivan on 2019-06-05.
 */
public class ColorTextView extends android.support.v7.widget.AppCompatTextView {

    private int padding = 0;
    private int color = 0;
    private int paddingVertical = 0;

    private String firstText = "";
    private String secondText = "";
    private int secondTextSize = 0;
    private Typeface secondTextTypeFace;


    public ColorTextView(Context context) {
        super(context);
    }

    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public ColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);

    }

    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ColorTextView);

        padding = attributes.getInt(R.styleable.ColorTextView_ctv_padding_start, 16);
        paddingVertical = attributes.getInt(R.styleable.ColorTextView_ctv_padding_vertical, 12);

        color = attributes.getColor(R.styleable.ColorTextView_ctv_color, ContextCompat.getColor(context, android.R.color.transparent));
        secondText = attributes.getString(R.styleable.ColorTextView_ctv_second_text);
        secondTextSize = attributes.getInt(R.styleable.ColorTextView_ctv_second_text_size, 12);
        firstText = getText().toString();


        /**
         * Getting font style for second text
         */
        if (!context.isRestricted() && attributes.hasValue(R.styleable.ColorTextView_ctv_second_font)) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    secondTextTypeFace = attributes.getFont(R.styleable.ColorTextView_ctv_second_font);
                } else {
                    secondTextTypeFace = ResourcesCompat.getFont(context, attributes.getResourceId(R.styleable.ColorTextView_ctv_second_font, -1));
                }
            } catch (UnsupportedOperationException | Resources.NotFoundException e) {
                // Expected if it is not a font resource.
            }
        }


        attributes.recycle();
    }


    private int convertDpToPixel(float dp) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }

    private int convertSpToPixel(float sp) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                sp,
                r.getDisplayMetrics()
        );
    }

    private void init() {
        String totalString = firstText + (TextUtils.isEmpty(secondText) ? "" : " " + secondText);
        setShadowLayer(convertDpToPixel(padding), 0, 0, 0);
        Spannable spannableString = new SpannableString(totalString.toUpperCase());
        spannableString.setSpan(new PaddingBackgroundColorSpan(
                        color, convertDpToPixel(padding), convertDpToPixel(paddingVertical)),
                0, totalString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (!TextUtils.isEmpty(secondText)) {
            spannableString.setSpan(new AbsoluteSizeSpan(convertSpToPixel(secondTextSize)), firstText.length(), totalString.length(), SPAN_EXCLUSIVE_EXCLUSIVE);

            if (secondTextTypeFace != null) {
                spannableString.setSpan(new CustomTypefaceSpan(secondTextTypeFace), firstText.length(), totalString.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        setText(spannableString);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
        init();
    }

    public void setSecondText(String text) {
        secondText = text;
        init();
    }


}