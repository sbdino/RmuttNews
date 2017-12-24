package ball.mac.no.rmuttnews;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ball.mac.no.rmuttnews.utility.MyPagerAdapter;

import static ball.mac.no.rmuttnews.R.drawable.map;

public class ServiceActivity extends AppCompatActivity {

    //    Explicit
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        Create Toolbar
        createToolbar();

//        Create TabLayout
        createTabLayout();


//        Create PageView
        createPagerView();

    }   // Main Method

    private void createPagerView() {
        viewPager = findViewById(R.id.pagerView);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void createTabLayout() {
        tabLayout = findViewById(R.id.tabLayoutName);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.follow).setText(R.string.follower));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.notification).setText(R.string.notification));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.news).setText(R.string.news));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.map).setText(R.string.maps));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_search).setText(R.string.search));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    private void createToolbar() {

        toolbar = findViewById(R.id.toolbarService);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayoutService);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                ServiceActivity.this,
                drawerLayout,
                R.string.open,
                R.string.close
        );

    }

}   // Main Class