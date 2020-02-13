package com.se_proj.aiopa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kewin on 2/24/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logIn = (Button) findViewById(R.id.btn);
        TextView signUp = (TextView) findViewById(R.id.textView2);

        signUp.setPaintFlags(signUp.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        logIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DatabaseHandler db = new DatabaseHandler(v.getContext());
                EditText user = (EditText) findViewById(R.id.username);
                EditText pass = (EditText) findViewById(R.id.password);
                String userValue = user.getText().toString();
                String passValue = pass.getText().toString();
                boolean accountMatch = db.validateUser(userValue, passValue);
                int charUserLength = userValue.length();
                int charPassLength = passValue.length();

                if (accountMatch) {
                    SharedPreferences sharedPreferences = getSharedPreferences("SP_Uname", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uname", userValue);
                    editor.putBoolean("loggedin", true);
                    editor.commit();

                    //Intent i = new Intent (LoginActivity.this, QRCode.class); i.putExtra("username_kew", userValue);
                    Intent toSuc1 = new Intent(LoginActivity.this, MainActivity.class);
                    String a = userValue;
                    toSuc1.putExtra("key",a);

                    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference uname_db = database.getReference("uname");
                    DatabaseReference pword_db = database.getReference("pword");
                    uname_db.setValue(userValue);
                    pword_db.setValue(passValue);*/
                    startActivity(toSuc1);
                } else if (userValue.equals("") && passValue.equals("")) {
                    Toast.makeText(LoginActivity.this, "Fields Empty", Toast.LENGTH_SHORT).show();
                }
                /*else if(charUserLength < 5 && charPassLength < 5){ // At least 6 characters anything less is too short
                    Toast.makeText(LoginActivity.this,"Characters Too Short", Toast.LENGTH_SHORT).show();
                }*/
                else {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent toSign = new Intent(LoginActivity.this, SignUp.class);
                startActivity(toSign);
            }
        });
    }
}
