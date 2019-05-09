package com.empty.ispy.SignPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.empty.ispy.Menu.ConThemeAct;
import com.empty.ispy.Menu.Home;
import com.empty.ispy.R;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInAct extends AppCompatActivity {

    ImageView bgone;
    Button btnget,btnget2;
    private FirebaseAuth mAuth;
    private EditText inputEmail, inputPassword;
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser!=null){
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }


        bgone = (ImageView) findViewById(R.id.bgone);
        btnget = (Button) findViewById(R.id.btnget);
        btnget2 = (Button) findViewById(R.id.btnget2);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);


        bgone.animate().scaleX(2).scaleY(2).setDuration(5000).start();

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SignInAct.this,SignUpAct.class);
                startActivity(a);
            }
        });


        btnget2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }



                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInAct.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Incorrect password or login", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(),"Success auth", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), ConThemeAct.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }
                        });
            }
        });


    }
}
