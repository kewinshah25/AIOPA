package com.se_proj.aiopa;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

    // static String user12;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "All_Accounts";

    private static final String TABLE_USERS = "Registerd_Accounts";

    // Account Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERS = "userName";
    private static final String KEY_PASS = "passWord";
    private static final String KEY_NAME = "name";
    private static final String KEY_BUDGET = "budget";
    private static final String KEY_EXPENSE = "expense";
    private static final String KEY_CALORIES = "calories";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_HEIGHT = "height";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)  {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERS + " TEXT,"
                + KEY_PASS + " TEXT,"+ KEY_NAME + " TEXT," + KEY_BUDGET + " INT," + KEY_EXPENSE + " INT," + KEY_CALORIES + " INT," + KEY_WEIGHT + " INT," + KEY_HEIGHT + " INT" + ", sex TEXT)";
        db.execSQL(CREATE_ACCOUNT_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);// Create tables again
    }

    // Adding new User
    void addUsers(Users users) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERS, users.getUser()); // User Name
        values.put(KEY_PASS, users.getPassword()); // Password
        values.put(KEY_NAME, users.getName());
        values.put(KEY_BUDGET, users.getBudget());
        values.put(KEY_EXPENSE, Integer.valueOf(0));
        values.put(KEY_WEIGHT, users.getWeight());
        values.put(KEY_HEIGHT, users.getHeight());
        values.put("sex", users.getSex());
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }


    void updateExpenditure(String uname, String expend) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE Registerd_Accounts SET expense = expense+ " + Integer.parseInt(expend) + " WHERE " + KEY_USERS + " = '" + uname + "';");
        db.close();
    }

    void resetExpenditure(String uname) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Registerd_Accounts SET expense = expense - expense WHERE userName = '" + uname + "';");
        db.close();
    }

    public Cursor getExpenditure1(String uname) {
        return getWritableDatabase().rawQuery("select expense from Registerd_Accounts where userName = '" + uname + "';", null);
    }

    public Cursor getBudget(String uname) {
        return getWritableDatabase().rawQuery("select budget from Registerd_Accounts where userName = '" + uname + "';", null);
    }
    public Cursor getSex(String uname) {
        return getWritableDatabase().rawQuery("select sex from Registerd_Accounts where userName = '" + uname + "';", null);
    }





    public void deleteAll() //Deletes all data in the database
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);
    }

    public boolean validateUser(String username, String password){
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + username +"'AND "+KEY_PASS+"='"+password+"'" ,  null);
        return (c.getCount() > 0);

        //user12 = username;
        /*return true;
        }
        else{return false;}*/
    }
    public boolean sameUser(String username){
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + username  + "'" ,  null);
        if (c.getCount() > 0) {
            return true;
        }
        else{return false;}
    }

    // public String showCurrentUser(){
    //     return user12;
    //}
}
