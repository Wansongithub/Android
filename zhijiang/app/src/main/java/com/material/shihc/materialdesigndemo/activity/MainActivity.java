package com.material.shihc.materialdesigndemo.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.material.shihc.materialdesigndemo.R;
import com.material.shihc.materialdesigndemo.fragment.Firstfrm;
import com.material.shihc.materialdesigndemo.fragment.Secondfrm;
import com.material.shihc.materialdesigndemo.fragment.Thirdfrm;
import com.material.shihc.materialdesigndemo.tabhostfrm.FragmentTabHost;
import com.material.shihc.materialdesigndemo.tabhostfrm.TabIndicatorView;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener,SearchView.OnQueryTextListener {

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;

    private ActionBarDrawerToggle mDrawerToggle;

    private NavigationView mNavigationView;

    private View parentLayout;

    private FragmentTabHost tabhost;

    private TabIndicatorView chatIndicator;
    private TabIndicatorView discoverIndicator;
    private TabIndicatorView meIndicator;

    private static final String TAB_CHAT = "chat";
    private static final String TAB_DISCOVER = "discover";
    private static final String TAB_ME = "me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        parentLayout = findViewById(R.id.parentLayout);

        initToolBar();
        //初始化侧边栏
        initNavigation();

        initTabHostFragment();
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }


    protected void initToolBar() {
            mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
            mToolbar.setTitle("首页");// 标题的文字需在setSupportActionBar之前，不然会无效
            // toolbar.setSubtitle("副标题");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /* 这些通过ActionBar来设置也是一样的，注意要在setSupportActionBar(toolbar);之后，不然就报错了 */
            // getSupportActionBar().setTitle("标题");
            // getSupportActionBar().setSubtitle("副标题");
            // getSupportActionBar().setLogo(R.drawable.ic_launcher);

        /* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过Activity的onOptionsItemSelected回调方法来处理
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });*/

    }

    private void initNavigation() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,0,0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.nv_main_navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //切换相应 Fragment 等操作
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(mNavigationView);
                return false;
            }
        });
    }
   private void initTabHostFragment(){
        // 1. 初始化TabHost
        tabhost=(FragmentTabHost)findViewById(android.R.id.tabhost);
        tabhost.setup(this,getSupportFragmentManager(),
                R.id.realtabcontent);

        // 2. 新建TabSpec
        TabHost.TabSpec spec =tabhost.newTabSpec(TAB_CHAT);
        chatIndicator = new TabIndicatorView(this);
        chatIndicator.setTabTitle("消息");
        chatIndicator.setTabIcon(R.mipmap.tab_icon_chat_normal,
                R.mipmap.tab_icon_chat_focus);
        spec.setIndicator(chatIndicator);

        tabhost.addTab(spec, Firstfrm.class, null);

        // 2. 新建TabSpec
        spec = tabhost.newTabSpec(TAB_DISCOVER);
        discoverIndicator = new TabIndicatorView(this);
        discoverIndicator.setTabIcon(R.mipmap.tab_icon_discover_normal,
                R.mipmap.tab_icon_discover_focus);
        discoverIndicator.setTabTitle("发现");
        spec.setIndicator(discoverIndicator);
        // 3. 添加TabSpec
        tabhost.addTab(spec, Secondfrm.class,null);

        // 2. 新建TabSpec
        spec = tabhost.newTabSpec(TAB_ME);
        meIndicator = new TabIndicatorView(this);
        meIndicator.setTabIcon(R.mipmap.tab_icon_me_normal,
                R.mipmap.tab_icon_me_focus);
        meIndicator.setTabTitle("我");
        spec.setIndicator(meIndicator);
        // 3. 添加TabSpec
        tabhost.addTab(spec, Thirdfrm.class, null);

        // 去掉分割线
        tabhost.getTabWidget().setDividerDrawable(android.R.color.white);

        // 初始化 tab选中
        tabhost.setCurrentTabByTag(TAB_CHAT);
        chatIndicator.setTabSelected(true);
        tabhost.setOnTabChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(android.os.Build.VERSION.SDK_INT > 11){
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);}
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(MainActivity.this, "action_share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
               search();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void search() {
        Toast.makeText(MainActivity.this, "action_search", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (mDrawerLayout.isDrawerOpen(mNavigationView)){
                mDrawerLayout.closeDrawer(mNavigationView);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }


/*
    private void showSnackbar() {
        Snackbar
                .make(parentLayout, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_action, this)
                .show(); // Don’t forget to show!
    }
*/

    @Override
    public void onTabChanged(String tag) {
        chatIndicator.setTabSelected(false);
        discoverIndicator.setTabSelected(false);
        meIndicator.setTabSelected(false);

        if (TAB_CHAT.equals(tag)) {
            chatIndicator.setTabSelected(true);
        } else if (TAB_DISCOVER.equals(tag)) {
            discoverIndicator.setTabSelected(true);
        } else if (TAB_ME.equals(tag)) {
            meIndicator.setTabSelected(true);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
       /* Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT
        ).show();
        return true;*/
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SearchActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("search",query);
        intent.putExtras(bundle);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}
