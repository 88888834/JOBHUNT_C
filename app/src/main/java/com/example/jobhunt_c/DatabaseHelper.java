package com.example.jobhunt_C;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_GENRE ="userTypeRadioGroup" ;
    public static final String COLUMN_CITY ="spinner_cities" ;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jobhunt.db";
    public static final String TABLE_NAME = "Users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String COLUMN_REMEMBER_ME = "remember_me";


    private static final String SQL_CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_REMEMBER_ME + " INTEGER DEFAULT 0, " + // Added comma here
                    COLUMN_CITY + " TEXT, " + // Added comma and fixed missing +
                    COLUMN_GENRE + " INTEGER DEFAULT 0)"; // Fixed the closing bracket and semicolon

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USERS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addUser(String username, String password, boolean rememberMe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_REMEMBER_ME, rememberMe ? 1 : 0);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result != -1;
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public int compterAnnoncesPourVille(String ville) {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        String tableName = "ads";
        String cityColumnName = "city";
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + cityColumnName + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{ville});

        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }

        return count;
    }
}
