package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homepage extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    public static FirebaseUser firebaseUser;
    DatabaseReference myref;
    public static String UserKey;


    private void getCurrentUserKey() {
        String email = firebaseUser.getEmail();
        final String[] Key = new String[1];

        myref=FirebaseDatabase.getInstance("https://gossip-32bb8-default-rtdb.firebaseio.com/").getReference("Users");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:  dataSnapshot.getChildren()){
                    User user=dataSnapshot1.getValue(User.class);
                    if(user.getEmail().equals(email)){
                        UserKey =dataSnapshot1.getKey();
                         Log.d("Key","Eita ki? : "+dataSnapshot1.getKey());
                         break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
//For toolbar/appbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//For Tablayout
        tabLayout = findViewById(R.id.HomepageTabLayout);
        //appBarLayout = findViewById(R.id.AppbarHomepage);
        viewPager = findViewById(R.id.ViewPager_homePage);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//Add fragments
        adapter.AddFragment(new Fragment_chats(), "Chats");
        adapter.AddFragment(new Fragment_Friends(), "Active Friends");
        adapter.AddFragment(new Fragment_Stories(), "Stories");
//Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//FirebaseUser
        firebaseUser = Login.getFirebaseUser();
        //Toast.makeText(getApplicationContext(), "Current User = "+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
//GetUserkey
        getCurrentUserKey();
        Toast.makeText(this, "MyKey: "+UserKey, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void AddFriends(MenuItem item) {
        Intent intent = new Intent(this, FindFriends.class);
        startActivity(intent);

    }
}