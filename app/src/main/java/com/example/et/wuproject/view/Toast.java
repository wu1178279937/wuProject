package com.example.et.wuproject.view;

import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.R.id.message;

/**
 * <p>
 * </p>
 *
 * @author:ET
 * @since：2016/11/10 15
 * @version: 2.5
 */

public class Toast {

     private static  final String CHECK_OP_NO_THOW="checkOpNoThrow";
     private static  final String OP_POST_NOTIFICATION="OP_POST_NOTIFICATION";
     private static  int checkNotification=-1;
    private  Object mToast;
     public static final int LENGTH_SHORT=0;
     public static final int LENGTH_LONG=1;

    private Toast(Context context,String message,int duration){
        try {
            if (checkNotification==-1){
                checkNotification=isNotificationEnabled(context)?0:1;
            }
            if (checkNotification==1){
                mToast=EToast.makeText(context,message,duration);
            }else{
                mToast= android.widget.Toast.makeText(context,message,duration);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Toast(Context context,int  resId,int duration){
        try {
            if (checkNotification==-1){
                checkNotification=isNotificationEnabled(context)?0:1;
            }
            if (checkNotification==1){
                mToast=EToast.makeText(context,resId,duration);
            }else{
                mToast= android.widget.Toast.makeText(context,resId,duration);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public static Toast makeText(Context context,String message,int duration){

          return  new Toast(context,message,duration);
     }
    public static Toast makeText(Context context,int  resId,int duration){

        return  new Toast(context,resId,duration);
    }


    public void show(){
        if (mToast instanceof Toast){
            ((Toast)mToast).show();
        }else if (mToast instanceof  android.widget.Toast){
            ((android.widget.Toast)mToast).show();
        }
    }
    public void cancel(){
        if (mToast instanceof Toast){
            ((Toast)mToast).cancel();
        }else if (mToast instanceof  android.widget.Toast){
            ((android.widget.Toast)mToast).cancel();
        }
    }


    private static  boolean isNotificationEnabled(Context context){
        AppOpsManager ops=(AppOpsManager)context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo=context.getApplicationInfo();
        String pkg=context.getApplicationContext().getPackageName();
        int uuid=appInfo.uid;
         Class appClsClass=null;

        try {
            appClsClass=Class.forName(AppOpsManager.class.getName());

            Method optMethd=appClsClass.getMethod(CHECK_OP_NO_THOW,Integer.TYPE,Integer.TYPE,String.class);
            Field optField=appClsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value= optField.getInt(Integer.class);

            return  (int)optMethd.invoke(ops,value,uuid,pkg)==AppOpsManager.MODE_ALLOWED;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  true;

    }
}
