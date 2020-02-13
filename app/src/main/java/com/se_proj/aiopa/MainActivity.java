package com.se_proj.aiopa;

        import android.content.Intent;
        import android.database.Cursor;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    StringBuffer buffer = new StringBuffer();
    StringBuffer buffer1 = new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        String uname = getSharedPreferences("SP_Uname", 0).getString("uname", "NA");

        Cursor c = db.getExpenditure1(uname);
        while (c.moveToNext()) {
            this.buffer.append(c.getString(c.getColumnIndex("expense")));

        }
        Cursor c1 = db.getBudget(uname);
        while (c1.moveToNext()) {
            buffer1.append(c1.getString(c1.getColumnIndex("budget")));
        }


        Button finance = (Button) findViewById(R.id.button);
        Button nutrition = (Button) findViewById(R.id.button1);
        Button fitness = (Button) findViewById(R.id.button2);
        final Intent i1 = new Intent(this,finance.class);
        final Intent i2 = new Intent(this,nutrition.class);
        final Intent i3 = new Intent(this,fitness.class);
        finance.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(android.view.View v) {
                                           startActivity(i1);
                                       }
                                   });
        nutrition.setOnClickListener(new View.OnClickListener() {
            public void onClick(android.view.View v) {
                startActivity(i2);
            }
        });
        fitness.setOnClickListener(new View.OnClickListener() {
            public void onClick(android.view.View v) {
                startActivity(i3);
            }
        });




        //Toast.makeText(this,buffer,Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,buffer1,Toast.LENGTH_SHORT).show();

    }
}
