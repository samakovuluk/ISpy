package com.empty.ispy.Game;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;

public class PlayGame extends AppCompatActivity {
    DatabaseReference def;
    EditText msg;
    Button send;
    ListView list;
    TextView timer;
    TextView word;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    int tt=0;
    ProgressDialog progressDialogg;
int r=0;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {



            timer.setText(String.valueOf(tt));

            timerHandler.postDelayed(this, 1000);
            tt-=1;
            if (r==tt){
                timerHandler.removeCallbacks(timerRunnable);
                msg.setEnabled(false);
                send.setEnabled(false);


                Intent intent = new Intent(PlayGame.this,Voice.class);
                startActivity(intent);


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        final ProgressDialog progressDialog = new ProgressDialog(PlayGame.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        progressDialogg = new ProgressDialog(getApplicationContext());
        msg = findViewById(R.id.msg);
        send = findViewById(R.id.send);
        list = findViewById(R.id.list);
        timer = findViewById(R.id.timer);
        word = findViewById(R.id.word);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time dtNow = new Time();
                dtNow.setToNow();
                DatabaseReference def = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start");
                def.child("msg").child(dtNow.format2445()).setValue(currentUser.getEmail()+":"+currentUser.getDisplayName()+":"+msg.getText().toString());

            }
        });

        final List<String> items = new ArrayList<String>();
        final ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items );
        DatabaseReference msf = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("msg");

        list.setAdapter(itemsAdapter);
        msf.addChildEventListener(new ChildEventListener() {
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



         DatabaseReference def = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start");
        def.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               progressDialog.dismiss();


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

        DatabaseReference wf = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("spy");
        wf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String q= (String) dataSnapshot.getValue();
                String w=(currentUser.getEmail());
                if (q.contains(w)){
                    word.setText("You are spy");


                }
                else {
                    DatabaseReference def = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("word");
                    def.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            word.setText(dataSnapshot.getValue().toString());
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


        final DatabaseReference timeer = FirebaseDatabase.getInstance().getReference().child(Code.code).child("start").child("timer");
        timeer.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                tt= Integer.parseInt(dataSnapshot.getValue().toString());
                System.out.println(tt);

                timer.setText(String.valueOf(tt));
                timerHandler.postDelayed(timerRunnable, 0);

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
}
