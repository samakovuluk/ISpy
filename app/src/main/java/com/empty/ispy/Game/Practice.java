package com.empty.ispy.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.empty.ispy.Menu.Home;
import com.empty.ispy.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Practice extends AppCompatActivity {
    TextView pagetitle, pagesubtitle;
    ImageView packagePlace;
    SeekBar packageRange;
    Animation atg, packageimg;
    Button btntake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        packageimg = AnimationUtils.loadAnimation(this, R.anim.packageimg);

        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        packagePlace = findViewById(R.id.packagePlace);

        packageRange = findViewById(R.id.packageRange);

        btntake =findViewById(R.id.btntake);

        btntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        packageRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 35){
                    pagetitle.setText("Create game");
                    pagesubtitle.setText("To connect other players");
                    packagePlace.setImageResource(R.drawable.icstarter);

                    // pass animation
                    packagePlace.startAnimation(packageimg);
                    pagetitle.startAnimation(atg);
                    pagesubtitle.startAnimation(atg);
                }
                else if(progress < 75){
                    pagetitle.setText("Waiting");
                    pagesubtitle.setText("Wait other players to play with them");
                    packagePlace.setImageResource(R.drawable.icbusinessplayer);

                    // pass animation
                    packagePlace.startAnimation(packageimg);
                    pagetitle.startAnimation(atg);
                    pagesubtitle.startAnimation(atg);
                }
                else if(progress <=100){
                    pagetitle.setText("Play");
                    pagesubtitle.setText("After connectings start game and enjoy");
                    packagePlace.setImageResource(R.drawable.icvip);

                    // pass animation
                    packagePlace.startAnimation(packageimg);
                    pagetitle.startAnimation(atg);
                    pagesubtitle.startAnimation(atg);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
