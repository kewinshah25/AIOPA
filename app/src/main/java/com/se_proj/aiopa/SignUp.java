package com.se_proj.aiopa;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView goHome = (TextView) findViewById(R.id.home);
        Button register = (Button) findViewById(R.id.reg);

        goHome.setPaintFlags(goHome.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        goHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent toMain = new Intent(SignUp.this, LoginActivity.class);
                startActivity(toMain);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DatabaseHandler db = new DatabaseHandler(v.getContext());
                Intent toMain = new Intent(SignUp.this, LoginActivity.class);
                EditText user = (EditText) findViewById(R.id.username);
                EditText pass = (EditText) findViewById(R.id.password);
                EditText name = (EditText) findViewById(R.id.name);
                EditText budget = (EditText) findViewById(R.id.budget);
                EditText weight = (EditText) findViewById(R.id.weight);
                EditText height = (EditText) findViewById(R.id.height);
                String userValue = user.getText().toString();
                String passValue = pass.getText().toString();
                String nameValue = name.getText().toString();
                String budgetValue = budget.getText().toString();
                String weightValue = weight.getText().toString();
                String heightValue = height.getText().toString();
                String sex = "Male";
                int charUserLength = userValue.length();
                int charPassLength = passValue.length();
                boolean userMatch = db.sameUser(userValue);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiog);
                if(radioGroup.getCheckedRadioButtonId()==(R.id.male))
                sex = "Male";
                else sex = "Female";

                if (userMatch) { //Checks to see if the UserName already exists
                    Toast.makeText(SignUp.this, "UserName Already Taken", Toast.LENGTH_LONG).show();
                }
                else if (userValue.equals("") && passValue.equals("")) {
                    Toast.makeText(SignUp.this, "Fields Empty", Toast.LENGTH_SHORT).show();
                }
                /*else if (charUserLength <= 5 && charPassLength <= 6) {
                    Toast.makeText(SignUp.this, "Characters too short", Toast.LENGTH_SHORT).show();
                }*/
                else {
                    db.addUsers(new Users(userValue, passValue, nameValue, budgetValue, weightValue, heightValue, sex));
                    //String a = db.showCurrentUser();
                    //Toast.makeText(SignUp.this, "bitch", Toast.LENGTH_SHORT);
                    //toMain.putExtra("key",a);
                    startActivity(toMain);
                }

            }

        });

    }
}
