package com.example.gossip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FriendRequests_Page extends AppCompatActivity {

    private ListView FriendRequestlistView;
    private List<User> FriendRequestList;
    private CustomAdapterUserListForAdding customAdapterUserListForAdding;
    private String currentUID = Login.getFirebaseUser().getUid();

    TextView TVRequest;

    CollectionReference db1, db2;
    int CountRequest = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests_page);

        TVRequest = findViewById(R.id.TextView_Reuests);
        FriendRequestlistView = findViewById(R.id.ListView_FriendRequestListView);

        //Database Connection
        db1 = FirebaseFirestore.getInstance().collection("Friend_Requests");
        db2 = FirebaseFirestore.getInstance().collection("users");
//List Initialization
        FriendRequestList = new ArrayList<>();
        customAdapterUserListForAdding = new CustomAdapterUserListForAdding(
                FriendRequests_Page.this, FriendRequestList);
        FriendRequestList.clear();
//Query for Data retrieval
        Query query = db1.whereEqualTo("SentTo", currentUID);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        CountRequest++;
//                        Toast.makeText(FriendRequests_Page.this,
//                                "Eitai:?>   " + CountRequest + "\n\n" + document.getData(),
//                                Toast.LENGTH_LONG).show();

                        String x= (String) document.get("User");
                        db2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                task.getResult()
                                for (QueryDocumentSnapshot document : task.getResult()){
                                    if (!document.getId().equals(x)) {
                                        Log.d("KKModi","inside if");
                                        Toast.makeText(FriendRequests_Page.this, "Inside Hoisi"+document.get("Full Name"), Toast.LENGTH_SHORT).show();
                                        String FN = (String) document.get("Full Name");
                                        String Email = (String) document.get("Email");
                                        String DoB = (String) document.get("Dob");
                                        User user = new User(document.getId(),FN, Email, DoB);
                                        FriendRequestList.add(user);
                                    }
                                }
                            }
                        });

                    }
                    FriendRequestlistView.setAdapter(customAdapterUserListForAdding);
                }
            }
        });

        TVRequest.setText("Requests " + CountRequest);
//List Clicker
        FriendRequestlistView.setOnItemClickListener((parent, view, position, id) -> {
            int itempos = position;
            //openUser(itempos);
            Toast.makeText(this, "position:> " + itempos, Toast.LENGTH_SHORT).show();
        });


    }

//    private void openUser(int itempos) {
//        User o = userList.get(itempos);
//        String name = o.getFullname();
//        String UID = o.getUID();
////        Toast.makeText(FindFriends.this, "Shob kichu nisi eibar pathabo", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(FindFriends.this, OpenUser.class);
//        intent.putExtra("UserName", name);
//        intent.putExtra("UID", UID);
//        startActivity(intent);
//    }

    @Override
    protected void onStart() {


//        db.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                FriendRequestlistView.setAdapter(customAdapterUserListForAdding);
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    if(document.get("SentTo").equals(currentUID)){
//                        String whoSentMe= (String) document.get("User");
//                        User user = new User(document.getId(), FN, Email, DoB);
//                        FriendRequestList.add(user);
//                    }
////                    if (!document.getId().equals(currentUID)) {
////                        String FN = (String) document.get("Full Name");
////                        String Email = (String) document.get("Emai");
////                        String DoB = (String) document.get("Dob");
////                        User user = new User(document.getId(), FN, Email, DoB);
////                        FriendRequestList.add(user);
////                    }
//                }
//                FriendRequestlistView.setAdapter(customAdapterUserListForAdding);
//            }
//        });
        super.onStart();

    }

    public void goback(View view) {
        this.finish();
    }
}