package com.example.habitapp_ravi2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TabLayout tabLayout;
    ViewPager2 pager;
    FragmentStateAdapter adapter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private androidx.appcompat.widget.Toolbar toolbar;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        tabLayout = findViewById(R.id.tablayout);
        pager = findViewById(R.id.viewpager);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        /*
            Fragment manager setup (for ViewPager2 and TabLayout)
        */
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentStateAdapter(fm, getLifecycle()){
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if(position == 0)
                    return new fragment1();
                else if(position == 1)
                    return new fragment2();
                else
                    return new fragment3();
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        } ;
        pager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        pager.setAdapter(null);
        adapter = new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()){
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if(position == 0)
                    return new fragment1();
                else if(position == 1)
                    return new fragment2();
                else
                    return new fragment3();
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        } ;
        pager.setAdapter(adapter);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch(item.getItemId()){
            case R.id.menuTab:
                Intent intent = new Intent(this, ModifyActivity.class);
                startActivity(intent);
                break;
            default:
                return false;
        }
        return false;

    }
}