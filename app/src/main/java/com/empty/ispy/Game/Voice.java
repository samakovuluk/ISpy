package com.empty.ispy.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.empty.ispy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Voice extends AppCompatActivity {
    ListView listView;
    List<Player> players;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_vol);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        button = findViewById(R.id.submit);

        listView=findViewById(R.id.list);
        final List<Player> pls = new ArrayList<Player>();

        final ListAdapter customAdapter = new ListAdapter(this, R.layout.item_vote, pls);


        listView.setAdapter(customAdapter);
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child(Code.code).child("setting").child("users");

        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pls.add(new Player(dataSnapshot.getValue().toString().split(":")[0],dataSnapshot.getValue().toString().split(":")[0],false));
                customAdapter.notifyDataSetChanged();
                System.out.println("asdsad");
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                pls.remove(new Player(dataSnapshot.getValue().toString().split(":")[0],dataSnapshot.getValue().toString().split(":")[0],false));
                customAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<Spys.players.size();i++){
                    final DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child(Code.code).child("result").child("users").child("user");

                    final int finalI = i;
                    dref.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            System.out.println("--------");
                            System.out.println(dataSnapshot.toString());


                            String nm=dataSnapshot.getKey();
                            String es=dataSnapshot.getValue().toString();
                            System.out.println(nm);
                            System.out.println(es);
                            System.out.println(es+":"+Spys.players.get(finalI).email.toString().replace("@","").replace(".",""));
                            if (nm.contains(Spys.players.get(finalI).email.toString().replace("@","").replace(".","")))
                            {

                                    int t=Integer.parseInt(es);
                                    t+=1;
                                    dref.child(nm).setValue(t);
                                    System.out.println("aaaaaaaaaaaaadddddddddd");


                                System.out.println("adaddddd");
                            }


                            System.out.println(dataSnapshot.getKey());
                            System.out.println(dref.getKey());
                            System.out.println("--------");

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

                }
                Intent intent = new Intent(getApplicationContext(),Result.class);
                startActivity(intent);

            }


        });
    }
}
