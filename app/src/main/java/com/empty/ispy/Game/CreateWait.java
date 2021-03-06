package com.empty.ispy.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.empty.ispy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateWait extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String [] s;
    TextView code;
    int n;
    String[] wor;
    TextView num;
    TextView name;
    Button cancel;
    Button start;
    ListView listView;

    FirebaseDatabase database ;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wait);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        code= findViewById(R.id.code);
        num =findViewById(R.id.num);
        name = findViewById(R.id.name);
        cancel = findViewById(R.id.cancel);
        start =findViewById(R.id.start);
        listView = findViewById(R.id.list);


        code.setText(Code.code);
        name.setText(currentUser.getDisplayName());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Code.code);
        myRef.child("setting").child("users").child(currentUser.getUid()).setValue(currentUser.getEmail()+":"+currentUser.getDisplayName());


        final List<String> items= new ArrayList<String>();


        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items );

        listView.setAdapter(itemsAdapter);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference productsRef = rootRef.child(Code.code).child("setting");
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child(Code.code).child("setting").child("users");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("count");
                dref.setValue(items.size());

                final DatabaseReference ran = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("spy").child("spy");
                final Random random = new Random();
                System.out.println(items.size());
                 n = random.nextInt(items.size());
                ran.setValue(items.get(n).toString());



                final DatabaseReference word = FirebaseDatabase.getInstance().getReference().child("word");
                word.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String r=dataSnapshot.getValue().toString();
                        wor=r.split(":");
                        DatabaseReference pp = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("word").child("word");

                        n = random.nextInt(wor.length);
                        pp.setValue(wor[n]);
                        System.out.println("assssssssssssssssssssss");
                        System.out.println(wor[n]);

                    }
                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    }
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                for (int i=0;i<items.size();i++) {
                    final DatabaseReference dr = FirebaseDatabase.getInstance().getReference().child(Code.code).child("result").child("users").child("user").child(items.get(i).split(":")[0]
                            .replace("@", "").replace(".", ""));
                    dr.setValue(0);
                }


                Intent intent = new Intent(getApplicationContext(),PlayGame.class);
                startActivity(intent);
                final DatabaseReference timer = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("timer");
                timer.child("timer").setValue(20);

                finish();

            }
        });

        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                items.add(dataSnapshot.getValue().toString());
                itemsAdapter.notifyDataSetChanged();
                System.out.println("asdsad");
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                items.remove(dataSnapshot.getKey());
                itemsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


}
