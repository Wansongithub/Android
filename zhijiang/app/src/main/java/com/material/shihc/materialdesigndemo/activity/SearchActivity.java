package com.material.shihc.materialdesigndemo.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;


//import com.dexafree.materialList.view.MaterialListView;
import com.material.shihc.materialdesigndemo.R;

/**
 * Created by wanga on 2015/11/7.
 */
public class SearchActivity extends ListActivity {
private Toolbar mToolBar;
  //  private  MaterialListView materialListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);

        mToolBar=(Toolbar)findViewById(R.id.id_toolbar);
        mToolBar.setTitle("搜索结果");

        //materialListView= (MaterialListView) findViewById(R.id.material_listview);

        Intent intent=getIntent();
        Bundle bundle =  intent.getExtras();
        String query= (String) bundle.get("search");




    }
}
