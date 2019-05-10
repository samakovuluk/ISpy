package com.empty.ispy.Game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.empty.ispy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        listView= findViewById(R.id.list);

        final List<String> ll = new ArrayList<String>();
        final ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ll );
        listView.setAdapter(itemsAdapter);

        DatabaseReference msf = FirebaseDatabase.getInstance().getReference().child(Code.code).child("result").child("users");


        msf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println(dataSnapshot.getValue().toString());
                ll.add(dataSnapshot.getValue().toString());

                itemsAdapter.notifyDataSetChanged();
                System.out.println("asdsad");
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ll.remove(dataSnapshot.getKey());
                ll.add(dataSnapshot.getValue().toString());
                itemsAdapter.notifyDataSetChanged();

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ll.remove(dataSnapshot.getKey());
                itemsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                ll.remove(dataSnapshot.getKey());
                ll.add(dataSnapshot.getValue().toString());
                itemsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        DatabaseReference msaf = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start");


        msaf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println(dataSnapshot.getValue().toString());

                if(!dataSnapshot.getKey().equals("msg")){
                ll.add(dataSnapshot.getValue().toString());
                itemsAdapter.notifyDataSetChanged();
                System.out.println("asdsad");}
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey()!="msg"){
                ll.remove(dataSnapshot.getKey());
                ll.add(dataSnapshot.getValue().toString());
                itemsAdapter.notifyDataSetChanged();}

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ll.remove(dataSnapshot.getKey());
                itemsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                ll.remove(dataSnapshot.getKey());
                ll.add(dataSnapshot.getValue().toString());
                itemsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }
}
