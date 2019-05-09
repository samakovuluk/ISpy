package com.empty.ispy.Game;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.empty.ispy.Generator.RandomString;
import com.empty.ispy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlaySettings extends AppCompatActivity {

    TextView nameuser, walletuser, review, network, plugins, myapps, mainmenus,
            pagetitle, pagesubtitle;

    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playsetting);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);

        nameuser = findViewById(R.id.nameuser);
        nameuser.setText(currentUser.getDisplayName());

        walletuser = findViewById(R.id.walletuser);
        walletuser.setText(currentUser.getEmail());

        imageView3 = findViewById(R.id.imageView3);

        review = findViewById(R.id.review);
        network = findViewById(R.id.network);
        plugins = findViewById(R.id.plugins);
        myapps = findViewById(R.id.myapps);
        mainmenus = findViewById(R.id.mainmenus);

        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        btnguide = findViewById(R.id.btnguide);

        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // pass an animation
        imageView3.startAnimation(atg);

        pagetitle.startAnimation(atgtwo);
        pagesubtitle.startAnimation(atgtwo);

        btnguide.startAnimation(atgthree);

        btnguide.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                create(1,2);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void create(int count, int spy){

        LocalDateTime now = LocalDateTime.now();
        String code = (new RandomString().getAlphaNumericString(6));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(code);
        myRef.child("setting").child("count").setValue(count);
        myRef.child("setting").child("spy").setValue(spy);


    }
}
