package com.example.et.wuproject;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

/**
 * <p>
 * </p>
 *
 * @author:ET
 * @since：2016/11/10 11
 * @version: 2.5
 */

public class DownloadRecevive extends BroadcastReceiver {

    private ImageView imgView;
    private DownloadManager downloadManager;

    public DownloadRecevive(DownloadManager downloadManager, ImageView imgView) {
        this.downloadManager = downloadManager;
        this.imgView = imgView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
