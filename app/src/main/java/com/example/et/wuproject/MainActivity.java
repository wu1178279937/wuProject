package com.example.et.wuproject;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.renderscript.RenderScript;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.et.wuproject.ui.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private View mDecorView;
    private Button btn;
    private DownloadManager mDownloadMananger;
    private long downLoadId;
    private ImageView mImageView;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           btn=(Button)findViewById(R.id.button);
        mDownloadMananger=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        mImageView=(ImageView)findViewById(R.id.imageView);
        linearLayout=(LinearLayout)findViewById(R.id.activity_main);

         DownloadRecevive recevive=new DownloadRecevive();
        IntentFilter intentFilter=new IntentFilter();
          intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
          intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
         registerReceiver(recevive,intentFilter);



    }

    public void OnNext(View view){

        com.example.et.wuproject.view.Toast.makeText(MainActivity.this,"dkfdskfdf",3000).show();
     DownloadManager.Request request=new DownloadManager.Request(Uri.parse("http://img04.tooopen.com/images/20130712/tooopen_17270713.jpg"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("下载图片....");
        downLoadId=  mDownloadMananger.enqueue(request);


     /*   Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this,btn,"sharebtn").toBundle());*/

        //overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom);
    }


    public class DownloadRecevive extends BroadcastReceiver {



        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
                Uri uri=mDownloadMananger.getUriForDownloadedFile(downLoadId);

                ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
                mImageView.setImageURI(uri);

                Palette.generateAsync(((BitmapDrawable)mImageView.getDrawable()).getBitmap(), new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch var=palette.getVibrantSwatch();

                        linearLayout.setBackgroundColor(var.getRgb());
                    }
                });


            }else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)){
                Toast.makeText(MainActivity.this,"dfdsfdf",Toast.LENGTH_LONG).show();
            }
        }
    }
}
