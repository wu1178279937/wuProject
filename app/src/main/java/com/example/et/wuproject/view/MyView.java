package com.example.et.wuproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>
 * </p>
 *
 * @author:ET
 * @since：2016/11/9 14
 * @version: 2.5
 */

public class MyView extends View {
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
       // mPaint.setShadowLayer(10,15,15, Color.RED);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#FF0000"));
      /* path.addCircle(300,200,100, Path.Direction.CW);
        path.addCircle(200,200,100, Path.Direction.CW);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);*/
       // canvas.drawPath(path, mPaint);
        drawText(canvas);

    }

    private void drawText(Canvas canvas){

        Path path = new Path();
        mPaint.setFakeBoldText(true);
        mPaint.setUnderlineText(true);
        mPaint.setTextSkewX((float)-0.25);
        mPaint.setStrikeThruText(true);
        mPaint.setTextScaleX(2);
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.FILL);
        Typeface typeface=Typeface.create("宋体",Typeface.BOLD);

          mPaint.setTypeface(typeface);
        path.addCircle(200,300,200, Path.Direction.CW);
        canvas.drawTextOnPath("我是一颗小小的石头",path,40,-20,mPaint);
    }
}
