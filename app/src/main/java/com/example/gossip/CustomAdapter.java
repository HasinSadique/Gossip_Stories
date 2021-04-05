package com.example.gossip;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//public class CustomAdapter extends ArrayAdapter<MyChats> {
//    private Activity context;
//    private List<Mychats> chatList;
//
//    public CustomAdapter(Activity context,List<Mycchats> chatList){
//        super(context,R.layout.sample_layout_mychats,chatList);
//        this.context=context;
//        this.chatList=chatList;
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater layoutInflater=context.getLayoutInflater();
//        View view=layoutInflater.inflate(R.layout.sample_layout_mychats,null,true);
//
//        MyChats myChats=chatList.get(position);
//
//        TextView t1=view.findViewById(R.id.TextView_UserFriendProfilename);
//        TextView t2=view.findViewById(R.id.CardView_UserChatMessage);
//
//        t1.setText(User.getName());
//        t2.setText(MyChat.getLastMessage());
//
//        return view;
//    }
//}
