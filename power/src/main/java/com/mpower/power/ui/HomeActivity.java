package com.mpower.power.ui;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mpower.power.R;
import com.mpower.power.adapters.DrawerAdapter;
import com.mpower.power.pojos.DrawerItem;

import java.util.ArrayList;


/**
 * Activity class of MPower Home,
 * Create navigation drawer here
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener {

    // UI components
    private ListView drawerListView;
    private DrawerLayout drawerLayout;
    private RelativeLayout drawerContainer;
    private RelativeLayout logout;
    private HomeActionBarDrawerToggle homeActionBarDrawerToggle;

    // drawer components
    private ArrayList<DrawerItem> drawerItemList;
    private DrawerAdapter drawerAdapter;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initDrawer();
        initDrawerList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (homeActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        homeActionBarDrawerToggle.syncState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        homeActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Initialize Drawer UI components
     */
    private void initDrawer() {
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerContainer = (RelativeLayout) findViewById(R.id.drawer_container);
        logout = (RelativeLayout) findViewById(R.id.home_logout);
        logout.setOnClickListener(this);

        // set custom sign out button
        TextView signOutTextView = (TextView) findViewById(R.id.sign_out_text);
        Typeface face = Typeface.createFromAsset(this.getAssets(), "fonts/vegur_2.otf");
        signOutTextView.setTypeface(face, Typeface.NORMAL);

        // set a custom shadow that overlays the main content when the drawer opens
        // set up drawer listener
        //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        homeActionBarDrawerToggle = new HomeActionBarDrawerToggle(this, drawerLayout);
        drawerLayout.setDrawerListener(homeActionBarDrawerToggle);
    }

    /**
     * Initialize Drawer list
     */
    private void initDrawerList() {
        // initialize drawer content
        // need to determine selected item according to the currently selected sensor type
        drawerItemList = new ArrayList<DrawerItem>();
        drawerItemList.add(new DrawerItem("Today", true));
        drawerItemList.add(new DrawerItem("Summary", false));
        drawerItemList.add(new DrawerItem("Switch board", false));

        drawerAdapter = new DrawerAdapter(HomeActivity.this, drawerItemList);
        drawerListView = (ListView) findViewById(R.id.drawer);

        if (drawerListView != null)
            drawerListView.setAdapter(drawerAdapter);

        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public void onClick(View v) {

    }


    /**
     * Handle open/close behaviours of Navigation Drawer
     */
    private class HomeActionBarDrawerToggle extends ActionBarDrawerToggle {
        public HomeActionBarDrawerToggle(Activity mActivity, DrawerLayout mDrawerLayout){
            super(mActivity, mDrawerLayout, R.drawable.ic_navigation_drawer, R.string.ns_menu_open, R.string.ns_menu_close);
        }

        @Override
        public void onDrawerClosed(View view) {
            invalidateOptionsMenu();
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            invalidateOptionsMenu();
        }
    }

    /**
     * Drawer click event handler
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Highlight the selected item, update the title, and close the drawer
            // update selected item and title, then close the drawer
            drawerLayout.closeDrawer(drawerContainer);

            //  reset content in drawer list
            for(DrawerItem drawerItem: drawerItemList) {
                drawerItem.setSelected(false);
            }

            if(position == 0) {
                drawerItemList.get(0).setSelected(true);
                getActionBar().setTitle("Today");
                loadToday();
            } else if(position==1) {
                drawerItemList.get(1).setSelected(true);
                getActionBar().setTitle("Summary");
                loadSummary();
            } else if(position == 2) {
                drawerItemList.get(2).setSelected(true);
                getActionBar().setTitle("Switch board");
            }

            drawerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Load my sensor list fragment
     */
    private void loadToday() {
        TodayFragment todayFragment = new TodayFragment();

        // fragment transitions
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main, todayFragment);
        transaction.commit();
    }

    /**
     * Load my sensor list fragment
     */
    private void loadSummary() {
        SummaryFragment summaryFragment = new SummaryFragment();

        // fragment transitions
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main, summaryFragment);
        transaction.commit();
    }

}
