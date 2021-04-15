package com.example.gossip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    public static FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
//For toolbar/appbar
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//For Tablayout
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
//FirebaseUser
        firebaseUser=Login.getFirebaseUser();
        Toast.makeText(getApplicationContext(), "Current User = "+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    public void AddFriends(MenuItem item) {
        Intent intent = new Intent(this, FindFriends.class);
        startActivity(intent);

    }
}