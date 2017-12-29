package ball.mac.no.rmuttnews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ball.mac.no.rmuttnews.utility.MyPagerAdapter;

public class ServiceActivity extends AppCompatActivity {

    //    Explicit
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SharedPreferences shared;


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



        //checkSession();
//      ********  getIntentStart*********

        //Intent intentLogin = getIntent();
        //String[] getLogin = intentLogin.getStringArrayExtra("Login");
        TextView tvEmail = (TextView)findViewById(R.id.tvEmail);
        TextView tvName =(TextView)findViewById(R.id.tvFnameLname);
        shared = getSharedPreferences("Rmuttnews", Context.MODE_PRIVATE);
        String email = shared.getString("email","");
        String fname = shared.getString("fname","");
        String lname = shared.getString("lname","");
        tvEmail.setText(email);
        tvName.setText(fname+"   "+lname);

        //onClickHome
        TextView tvHome = (TextView)findViewById(R.id.tvHome);
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                viewPager.setCurrentItem(0);
            }
        });

        //onClinkFollow
        TextView tvFollow = (TextView)findViewById(R.id.tvFollow);
        tvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                Intent followIntent = new Intent(ServiceActivity.this,FollowActivity.class);
                startActivity(followIntent);

            }
        });

        //onClickLogout
        Button btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();
                Intent logOutIntent = new Intent(ServiceActivity.this,LoginActivity.class);
                startActivity(logOutIntent);
                finish();
            }
        });

    }   // Main Method


    //    Create PageView
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
//    Create TabLayout
    private void createTabLayout() {
        tabLayout = findViewById(R.id.tabLayoutName);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.users));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.alarm));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.newspaper));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.map2));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.search));

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

//    create toolbar
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