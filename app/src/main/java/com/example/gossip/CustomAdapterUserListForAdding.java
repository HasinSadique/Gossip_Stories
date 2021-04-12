package com.example.gossip;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class CustomAdapterUserListForAdding extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public CustomAdapterUserListForAdding(Activity context, List<User> userList) {
        super(context, R.layout.sample_layout_user, userList);
        this.context = context;
        this.userList = userList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater  layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout_user,null,true);

        User user=userList.get(position);

        TextView t1=view.findViewById(R.id.TextView_NameShow);

        t1.setText("Name: "+user.getFullname());

        return view;
    }
}