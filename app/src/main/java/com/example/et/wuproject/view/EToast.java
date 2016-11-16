package com.example.et.wuproject.view;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.et.wuproject.R;


/**
 * <p>
 * </p>
 *
 * @author:ET
 * @since：2016/11/10 11
 * @version: 2.5
 */

public class EToast {

    private static final int LENTTH_SHORT=0;
    private static final int LENTTH_LONG=1;
    private static  EToast mResult;
    private final  int ANIMATION_DURATION=600;
    private static TextView mTextView;
    private ViewGroup container;
    private View view;
    private int HIDE_DELAY=2000;
    private LinearLayout mContainer;
    private AlphaAnimation alphaAnimationOut;
    private AlphaAnimation alphaAnimationIn;
    private boolean isShow=false;
    private static  Context mContext;
    private Handler mHandler=new Handler();


   private EToast(Context context){
       mContext=context;
       container=(ViewGroup)((Activity)context).findViewById(android.R.id.content);
       view=((Activity)context).getLayoutInflater().inflate(R.layout.etoast,container);
        mContainer=(LinearLayout)view.findViewById(R.id.mbContainer);
       mContainer.setVisibility(View.GONE);
       mTextView=(TextView)view.findViewById(R.id.mbMessage);


   }

    public static EToast makeText(Context context,String message,int HIDE_DELAY){
        if (mResult==null){
            mResult=new EToast(context);
        }else{
             if (!mContext.getClass().getName().equals(context.getClass().getName())){
                 mResult=new EToast(context);
             }
        }
            if (HIDE_DELAY==LENTTH_LONG){
                mResult.HIDE_DELAY=2500;

            }else{
                mResult.HIDE_DELAY=1500;
            }
           mTextView.setText(message);
         return  mResult;

    }

    public static EToast makeText(Context context,int  resId,int HIDE_DELAY){
           String msg="";
            msg=context.getResources().getString(resId);
        return  makeText(context,msg,HIDE_DELAY);

    }

    public void show(){
        if (isShow){
            return;
        }
        alphaAnimationIn=new AlphaAnimation(0.0f,1.0f);
        alphaAnimationOut=new AlphaAnimation(1.0f,0.0f);
        alphaAnimationOut.setDuration(ANIMATION_DURATION);
        alphaAnimationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isShow=false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mContainer.setVisibility(View.VISIBLE);
         alphaAnimationIn.setDuration(ANIMATION_DURATION);
        mContainer.startAnimation(alphaAnimationIn);
         mHandler.postDelayed(mHideRunnable,HIDE_DELAY);



    }

     private final  Runnable mHideRunnable=new Runnable() {
         @Override
         public void run() {
             mContainer.startAnimation(alphaAnimationOut);
         }
     };

     public void cancel(){
         if (isShow){
             isShow=false;
             mContainer.setVisibility(View.GONE);
             mHandler.removeCallbacks(mHideRunnable);
         }

     }

    public void reset(){
        mResult=null;
    }

    public void setText(CharSequence s){
        if (mResult==null){
            return;
        }
        TextView textView=(TextView)view.findViewById(R.id.mbMessage);
        if (textView==null)
            throw new RuntimeException("This Toast was not created");
         textView.setText(s);
    }


    public void setText(int resId){
        setText(mContext.getText(resId));
    }



}
