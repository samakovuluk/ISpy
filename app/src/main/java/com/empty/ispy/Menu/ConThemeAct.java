package com.empty.ispy.Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.empty.ispy.R;
public class ConThemeAct extends AppCompatActivity {

    String getThemeku;
    String themeku = "";
    String SHARED_PREFS = "codeTheme";

    TextView subtitlepage;
    Button btncontinue;
    ImageView icontheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_theme);

        subtitlepage = findViewById(R.id.subtitlepage);
        btncontinue = findViewById(R.id.btncontinue);
        icontheme = findViewById(R.id.icontheme);

        changeOurTheme();
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent a = new Intent(getApplicationContext(), Home.class);
               startActivity(a);
               finish();
            }
        });
    }

    public void changeOurTheme() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        getThemeku = sharedPreferences.getString(themeku, "");


            icontheme.setImageResource(R.drawable.s1);
            btncontinue.setBackgroundResource(R.drawable.bggreen);
            subtitlepage.setText("Spies are an endangered profession. Social networks do everything for them now.");


    }
}


