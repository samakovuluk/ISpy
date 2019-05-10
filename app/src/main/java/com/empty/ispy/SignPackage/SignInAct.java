package com.empty.ispy.SignPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.empty.ispy.Chat.MainActivity;
import com.empty.ispy.Chat.data.SharedPreferenceHelper;
import com.empty.ispy.Chat.data.StaticConfig;
import com.empty.ispy.Chat.model.User;
import com.empty.ispy.Menu.ConThemeAct;
import com.empty.ispy.Menu.Home;
import com.empty.ispy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.HashMap;
import java.util.regex.Pattern;

public class SignInAct extends AppCompatActivity {

    ImageView bgone;
    Button btnget,btnget2;
    private FirebaseAuth mAuth;
    private EditText inputEmail, inputPassword;




    private static String TAG = "LoginActivity";
    FloatingActionButton fab;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private EditText editTextUsername, editTextPassword;
    private LovelyProgressDialog waitingDialog;



    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private boolean firstTimeAccess;
    Button signup;

// ...
// Initialize Firebase Auth
@Override
protected void onStop() {
    super.onStop();
    if (mAuthListener != null) {
        mAuth.removeAuthStateListener(mAuthListener);
    }
}


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    private void initFirebase() {
        //Khoi tao thanh phan de dang nhap, dang ky
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    StaticConfig.UID = user.getUid();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    if (firstTimeAccess) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        SignInAct.this.finish();
                    }
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                firstTimeAccess = false;
            }
        };
    }
    public static FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

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
                final String email = inputEmail.getText().toString();
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
                                    saveUserInfo();
                                    Toast.makeText(getApplicationContext(),"Success auth", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), ConThemeAct.class);
                                    startActivity(intent);


                                }
                            }
                        });
            }
        });
        firstTimeAccess = true;
        initFirebase();

    }

    void saveUserInfo() {
        FirebaseDatabase.getInstance().getReference().child("user/" + StaticConfig.UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseUser uu= mAuth.getCurrentUser();
                HashMap hashUser = (HashMap) dataSnapshot.getValue();
                User userInfo = new User();
                userInfo.name = uu.getDisplayName();
                userInfo.email = uu.getEmail();
                userInfo.avata = (String) hashUser.get("avata");
                SharedPreferenceHelper.getInstance(SignInAct.this).saveUserInfo(userInfo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
