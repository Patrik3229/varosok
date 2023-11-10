package com.example.varosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "varosok.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "varosok";
    private static final String COL_ID = "id";
    private static final String COL_NEV = "nev";
    private static final String COL_ORSZAG = "orszag";
    private static final String COL_LAKOSSAG = "lakossag";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE_NAME + " (" + COL_ID +
                " integer primary key autoincrement, " + COL_NEV
                + " text not null," + COL_ORSZAG + " text not null," + COL_LAKOSSAG + " integer not null);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME
        );
        onCreate(sqLiteDatabase);
    }

    public boolean rogzites(String nev, String orszag, int lakossag){ // String alkohol int alkohol helyett
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_ORSZAG, orszag);
        values.put(COL_LAKOSSAG, lakossag);
        long result = database.insert(TABLE_NAME, null, values);
        if (result != -1){
            return true;
        }
        return false;
    }

    public Cursor adatLekerdezes(String orszag) {
        SQLiteDatabase database = this.getReadableDatabase();

        // Define the selection criteria
        String selection = COL_ORSZAG + " = ?";

        // Define the selection arguments
        String[] selectionArg = {orszag};

        // Perform the query with the specified criteria
        return database.query(TABLE_NAME, new String[] {COL_ID, COL_NEV, COL_ORSZAG, COL_LAKOSSAG},
                selection, selectionArg, null, null, null);
    }
}
