package com.example.et.wuproject.ui;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;

import com.example.et.wuproject.R;
import com.example.et.wuproject.bean.User;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class SecondActivity extends AppCompatActivity {

    private ShortcutManager mShortcutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        getWindow().setEnterTransition(new Fade().setDuration(1000));
//        getWindow().setExitTransition(new Fade().setDuration(1000));
    }

    public void onAdd (View view){

        Realm realm=Realm.getDefaultInstance();
          realm.beginTransaction();
        User user=realm.createObject(User.class);
          user.setName("zhangsan");
          user.setAge("18");
          user.setSex("1");
          realm.commitTransaction();

        selectItem();


    }
    public void onUpdate (View view){


    }

    public void onDetele (View view){

        Realm realm=Realm.getDefaultInstance();
        final RealmResults<User> users=realm.where(User.class).findAllAsync();
        if (users!=null&&!users.isEmpty()){
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    users.deleteFirstFromRealm();
                    selectItem();
                }
            });
        }


       /* List<ShortcutInfo> infos = mShortcutManager.getPinnedShortcuts();
        for (ShortcutInfo info : infos) {
            if (info.getId().equals("id" + 2)) {
                mShortcutManager.disableShortcuts(Arrays.asList(info.getId()), "暂无该联系人");
            }
        }
        mShortcutManager.removeDynamicShortcuts(Arrays.asList("id" + 2));*/
    }

    public void onDisable (View view){

    }

    private void selectItem(){
        Realm realm=Realm.getDefaultInstance();
        RealmResults<User>users=realm.where(User.class).findAllSorted(new String[]{"name","age","sex"}, new Sort[]{Sort.ASCENDING,Sort.ASCENDING,Sort.ASCENDING});
        if (users!=null&&!users.isEmpty()){

            for (User user:users){
                Logger.d("name="+user.getName()+", age="+user.getAge()+", sex="+user.getSex());
            }
        }
    }


}
