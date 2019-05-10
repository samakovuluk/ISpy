package com.empty.ispy.Game;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.empty.ispy.Chat.MainActivity;
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
    ImageView imageView3, crt, message;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playsetting);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        myDialog = new Dialog(this);




        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);

        nameuser = findViewById(R.id.nameuser);
        try{
            nameuser.setText(currentUser.getDisplayName());

        }
        catch (Exception e){

        }

        message=findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("asddddddddddddddddddddd");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

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

        crt= findViewById(R.id.crt);
        crt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                create(1,2,v);
            }
        });

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

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void create(int count, int spy, View view){

        ShowPopup(view);




    }
    int n,m;
    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;

        myDialog.setContentView(R.layout.dialog_setcreate);
        NumberPicker numberPicker =(NumberPicker) myDialog.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(23);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                n=newVal;

            }
        });

        NumberPicker numberPicker2 =(NumberPicker) myDialog.findViewById(R.id.numberPicker2);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(5);
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                m=newVal;
            }
        });

        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(PlaySettings.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setTitle("ProgressDialog"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                myDialog.dismiss();

                creategame(n,m,v,progressDialog);

            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void creategame(int n , int m, View view,ProgressDialog progressDialog){

        LocalDateTime now = LocalDateTime.now();
        String code = (new RandomString().getAlphaNumericString(6));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(code);
        myRef.child("setting").child("count").setValue(m);
        myRef.child("setting").child("spy").setValue(n);

        progressDialog.setTitle(code);
        progressDialog.setMessage("Success");



    }


}


