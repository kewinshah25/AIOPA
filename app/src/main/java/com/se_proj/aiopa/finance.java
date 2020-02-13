package com.se_proj.aiopa;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.Buffer;

public class finance extends AppCompatActivity {
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        //Toast.makeText(this,"hi finance",Toast.LENGTH_SHORT).show();
        uname = finance.this.getSharedPreferences("SP_Uname", 0).getString("uname", "NA");
        //Toast.makeText(this,"hi "+uname,Toast.LENGTH_SHORT).show();
        final EditText editText = (EditText) findViewById(R.id.expenditure);
        ((Button) findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editText.getText().toString().equals("") || editText.getText().toString().equals(null)) {
                    Toast.makeText(finance.this, "Please enter expenditure", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    uname = finance.this.getSharedPreferences("SP_Uname", 0).getString("uname", "NA");
                }
                DatabaseHandler db = new DatabaseHandler(finance.this);
                db.updateExpenditure(uname, editText.getText().toString());

                Cursor c1 = db.getExpenditure1(uname);
                StringBuffer buffer1 = new StringBuffer();
                while (c1.moveToNext()) {
                    buffer1.append(c1.getString(c1.getColumnIndex("expense")));
                }
                Toast.makeText(finance.this, "Expen: "+buffer1, Toast.LENGTH_SHORT).show();

                Cursor c2 = db.getBudget(uname);
                StringBuffer buffer2 = new StringBuffer();
                while (c2.moveToNext()) {
                    buffer2.append(c2.getString(c2.getColumnIndex("budget")));
                }
                Toast.makeText(finance.this, "Budget: "+buffer2, Toast.LENGTH_SHORT).show();
            }
        });
        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    uname = finance.this.getSharedPreferences("SP_Uname", 0).getString("uname", "NA");
                DatabaseHandler db = new DatabaseHandler(finance.this);
                db.resetExpenditure(uname);


                //Toast.makeText(finance.this, buffer1, Toast.LENGTH_SHORT).show();

            }
        });
        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent i1 = new Intent(finance.this, finance_stats.class);
                startActivity(i1);
            }
            });


    }
}
