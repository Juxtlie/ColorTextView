package com.colortextviewlibrary;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.style.LineBackgroundSpan;

/**
 * Created by on 2019-05-27.
 */
public class PaddingBackgroundColorSpan implements LineBackgroundSpan {
    private int mBackgroundColor;
    private int mPaddingHorizontal;
    private int mPaddingVertical;
    private Rect mBgRect;

    public PaddingBackgroundColorSpan(int backgroundColor, int paddingHorizontal, int paddingVertical) {
        super();
        mBackgroundColor = backgroundColor;
        mPaddingHorizontal = paddingHorizontal;
        mPaddingVertical = paddingVertical;
        // Precreate rect for performance
        mBgRect = new Rect();
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        final int textWidth = Math.round(p.measureText(text, start, end));
        final int paintColor = p.getColor();


        // Draw the background
        mBgRect.set(left - mPaddingHorizontal,
                top - (lnum == 0 ? mPaddingVertical / 2 : (mPaddingVertical  / 4)),
                left + textWidth + mPaddingHorizontal,
                bottom + mPaddingVertical/ 2);
        p.setColor(mBackgroundColor);
        c.drawRect(mBgRect, p);
        p.setColor(paintColor);
    }

}