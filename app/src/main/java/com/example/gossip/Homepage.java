package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class Homepage extends AppCompatActivity {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    public static FirebaseUser firebaseUser;
    public static String UserKey;
    private String userID;
    private String fullname,email,birthday;

    CircleImageView ProPic;

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
        userID = firebaseUser.getUid();
//GetUserkey
        Toast.makeText(this, "MyKey: " + userID, Toast.LENGTH_LONG).show();
        getDetails(userID);


    }

    private void getDetails(String userID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> documentSnapshotTask = db.collection("users")
                .document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                fullname= (String) document.get("Full Name");
                                email= (String) document.get("Email");
                                birthday= (String) document.get("Dob");
                                Toast.makeText(Homepage.this, "Data:\nFullname: "+fullname+"\nEmail: "+email+"\nBirthday: "+birthday, Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("TAG", "No such document");
                            }
                        } else {
                            Log.d("TAG", "get failed with ", task.getException());
                        }
                    }
                });
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

    public void PRO(View view) {
        //Account Page
        Intent intent= new Intent(this, MyProfile.class);
        Toast.makeText(this, "ProPic Clicked", Toast.LENGTH_SHORT).show();
    }
}