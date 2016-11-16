package com.example.et.wuproject.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author:ET
 * @since：2016/11/2 15
 * @version: 2.5
 */

public class InstallService extends AccessibilityService {


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();


    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
             checkInstall(event);
    }

    @Override
    public void onInterrupt() {

    }

    private void checkInstall(AccessibilityEvent event) {
        AccessibilityNodeInfo source=event.getSource();

        if (source!=null){
            boolean installPackage=source.getPackageName().equals("com.android.packageinstaller");
            if (installPackage){
                installAPK(event);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void installAPK(AccessibilityEvent event) {
        AccessibilityNodeInfo source=getRootInActiveWindow();
        List<AccessibilityNodeInfo>replaceInfos=source.findAccessibilityNodeInfosByText("替换");
        nextClick(replaceInfos);
         List<AccessibilityNodeInfo>nextInfos=source.findAccessibilityNodeInfosByText("下一步");
          nextClick(nextInfos);
        List<AccessibilityNodeInfo>installInfos=source.findAccessibilityNodeInfosByText("安装");
         nextClick(installInfos);
        List<AccessibilityNodeInfo>openInfos=source.findAccessibilityNodeInfosByText("打开");
        nextClick(openInfos);
         runInBack(event);

    }


    private void runInBack(AccessibilityEvent event) {
        event.getSource().performAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

     private void nextClick(List<AccessibilityNodeInfo> nodeInfos){
         if (nodeInfos!=null){
             for (AccessibilityNodeInfo info:nodeInfos){
                 if (info.isEnabled()&&info.isClickable()){
                     info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                 }
             }
         }
     }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private boolean checkTitle(AccessibilityNodeInfo info){
        List<AccessibilityNodeInfo>infos=getRootInActiveWindow().findAccessibilityNodeInfosByViewId("@id/app_name");
          for (AccessibilityNodeInfo tempInfo:infos){
              if (tempInfo.getClassName().equals("android.widget.TextView")){
                  return true;
              }
          }
        return  false;
    }




}
