package com.empty.ispy.Generator;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RandomString {

    FirebaseAuth mAuth;

   FirebaseUser currentUser;
    // function to generate a random string of length n
   public  String getAlphaNumericString(int n)
    {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();



        // chose a Character random from this String
        String AlphaNumericString = currentUser.getUid()+currentUser.getDisplayName();

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }



}