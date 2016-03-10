package com.material.shihc.materialdesigndemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wanga on 2015/11/6.
 */
public class Firstfrm extends Fragment{

    private TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       tv=new TextView(getContext());
        tv.setText("我是第一个Fragment");
        return tv;
    }
}
