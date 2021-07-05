 package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindFriends extends AppCompatActivity {

    EditText EditText_SearchFriends;

    private ListView listView;
    private List<User> userList;
    private CustomAdapterUserListForAdding customAdapterUserListForAdding;
    private String currentUID = Login.getFirebaseUser().getUid();

    CollectionReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        EditText_SearchFriends = findViewById(R.id.EditText_SearchFriends);
        //for database connection
        db = FirebaseFirestore.getInstance().collection("users");
        //getUsers();
        //checkFriends();
        userList = new ArrayList<>();
        customAdapterUserListForAdding = new CustomAdapterUserListForAdding(FindFriends.this, userList);
        listView = findViewById(R.id.ListViewUsers);
        //Toast.makeText(FindFriends.this, "Heelllooo jee", Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener((parent, view, position, id) -> {
            int itempos = position;
            openUser(itempos);
//            Toast.makeText(this, "clicked position: "+itempos, Toast.LENGTH_LONG).show();
        });
    }

    private void openUser(int itempos) {
        User o = userList.get(itempos);
        String name = o.getFullname();
        String UID = o.getUID();
//        Toast.makeText(FindFriends.this, "Shob kichu nisi eibar pathabo", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(FindFriends.this, OpenUser.class);
        intent.putExtra("UserName", name);
        intent.putExtra("UID", UID);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        userList.clear();
        db.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //listView.setAdapter(customAdapterUserListForAdding);
                for (QueryDocumentSnapshot document : task.getResult()) {
                    if (!document.getId().equals(currentUID)) {
                        String FN = (String) document.get("Full Name");
                        String Email = (String) document.get("Email");
                        String DoB = (String) document.get("Dob");
                        User user = new User(document.getId(),FN, Email, DoB);
                        userList.add(user);
                    }
                }
                listView.setAdapter(customAdapterUserListForAdding);
            }
        });
        super.onStart();

    }

    public void goback(View view) {
        finish();
    }

    public void OpenFriendRequests(View view) {
        Intent intent=new Intent(this,FriendRequests_Page.class);
        startActivity(intent);
    }
}