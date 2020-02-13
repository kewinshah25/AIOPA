package com.se_proj.aiopa;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class nutrition extends AppCompatActivity {

    Button mOrder;
    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        mOrder = (Button) findViewById(R.id.btnOrder);
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);

        listItems = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[listItems.length];


        final DatabaseHandler db = new DatabaseHandler(this);
        SQLiteDatabase a = db.getReadableDatabase();
        a.execSQL("DROP TABLE IF EXISTS food_items");
        a.execSQL("CREATE TABLE food_items (food TEXT, cals INTEGER)");



        a.close();


        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(nutrition.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mUserItems.contains(position)) {
//                                mUserItems.add(position);
//                            }
//                        } else if (mUserItems.contains(position)) {
//                            mUserItems.remove(position);
//                        }
                        if(isChecked){
                            mUserItems.add(position);
                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        int total_cal = 0;
                        for (int i = 0; i < mUserItems.size(); i++) {
                            String str = listItems[mUserItems.get(i)].toString();
                            int start = str.indexOf("(");
                            int end = str.indexOf("cals");
                            String substring = str.substring(start+1,end-1);
                            //Toast.makeText(nutrition.this,substring,Toast.LENGTH_SHORT).show();
                            int cal = Integer.parseInt(substring);

                            db.getReadableDatabase().execSQL("INSERT INTO food_items VALUES ('"+ listItems[mUserItems.get(i)].toString() +"', "+cal+")");


                            total_cal = total_cal + cal;

                            //item = item + listItems[mUserItems.get(i)];
                            //if (i != mUserItems.size() - 1) {item = item + ", "; } //remove last comma
                        }
                        item = Integer.toString(total_cal);
                        mItemSelected.setText("Calories consumed: "+item);
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            mUserItems.clear();
                            mItemSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }
}
