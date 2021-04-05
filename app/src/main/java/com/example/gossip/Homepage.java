package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Homepage extends AppCompatActivity {

    private TabLayout tabLayout;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        tabLayout = findViewById(R.id.HomepageTabLayout);
        //appBarLayout = findViewById(R.id.AppbarHomepage);
        viewPager =findViewById(R.id.ViewPager_homePage);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Add fragments
        adapter.AddFragment(new Fragment_chats(),"Chats");
        adapter.AddFragment(new Fragment_Friends(),"Active Friends");
        adapter.AddFragment(new Fragment_Stories(),"Stories");
        //Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

}