package com.material.shihc.materialdesigndemo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.material.shihc.materialdesigndemo.R;

/**
 * Created by wanga on 2015/11/8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.common_toolbar);
        //初始化toobar
        initToolBar();
    }

   protected abstract void initToolBar() ;
}
