package com.se_proj.aiopa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fitness extends AppCompatActivity {
    double cal;
    int BMR;
    TextView mItemSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        //Toast.makeText(this,"hi fitness",Toast.LENGTH_SHORT).show();
        Button mOrder = (Button) findViewById(R.id.btnOrder);
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);
        int age;
        String height;
        String weight;
        String gender;
        char genderChar;
        boolean male;
        String exercise;
        boolean none = false;
        boolean light = false;
        boolean moderately = false;
        boolean intensely = false;
        boolean five;
        String uname = getSharedPreferences("SP_Uname", 0).getString("uname", "NA");
        DatabaseHandler db = new DatabaseHandler(this);
        Cursor c = db.getSex(uname);
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            buffer.append(c.getString(c.getColumnIndex("sex")));
        }
        String sex = buffer.toString();
        //Toast.makeText(this, sex, Toast.LENGTH_SHORT).show();

        //Determining your BMR

        //age = keyboard.nextInt();


        c = db.getWritableDatabase().rawQuery("select weight from Registerd_Accounts where userName = '" + uname + "';", null);
        buffer.delete(0, buffer.length());
        while (c.moveToNext()) {
            buffer.append(c.getString(c.getColumnIndex("weight")));
        }
        weight = buffer.toString();
        //Toast.makeText(this, weight, Toast.LENGTH_SHORT).show();
        c = db.getWritableDatabase().rawQuery("select height from Registerd_Accounts where userName = '" + uname + "';", null);
        buffer.delete(0, buffer.length());
        while (c.moveToNext()) {
            buffer.append(c.getString(c.getColumnIndex("height")));
        }
        height = buffer.toString();

        //genderChar = gender.charAt(0);

        //male = genderChar == 'M';

        if (sex.equals("male"))
            BMR = (int) (66 + (6.23 * Integer.parseInt(weight)) + (12.7 * Integer.parseInt(height)) - (6.8 * 20));
        else
            BMR = (int) (665 + (4.35 * Integer.parseInt(weight)) + (4.7 * Integer.parseInt(height)) - (4.7 * 20));

        //Show BMR
        //Level of Exercises
        cal = Math.round(BMR * 1.9);


        //exercise = keyboard.nextLine();
        //none = keyboard.nextLine() != null;

        //System.out.println("Your daily calorie needs " + cal);
        mOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(android.view.View v) {
                mItemSelected.setText("Your BMR: "+BMR+" \n\nYou need to burn "+cal+" calories daily.");
            }
        });

    }
}
