package com.empty.ispy.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.empty.ispy.Game.PlaySettings;
import com.empty.ispy.Game.Practice;
import com.empty.ispy.R;
import com.empty.ispy.SignPackage.SignInAct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.widget.ImageView;

public class Home extends AppCompatActivity {
    Button but1,but2, but3, but4;
    ImageView imageView2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Practice.class);
                startActivity(intent );


            }
        });


        but4 = findViewById(R.id.but4);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaySettings.class);
                startActivity(intent);


            }
        });

        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignInAct.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
