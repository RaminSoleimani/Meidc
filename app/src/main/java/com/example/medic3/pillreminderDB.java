package com.example.medic3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class pillreminderDB {

    // columns of the table
    public static final String KEY_ROWID="_id";
    public static final String KEY_Pillname="pill_name";
    public static final String KEY_START="_start";
    public static final String KEY_Duration="_duration";
    public static final String KEY_alarm="alarm_id";

    // database name
    private static final String DATABASE_NAME="PillReminderDB";

    // tablename
    private static final String DATABASE_TABLE="PillReminderTable";

     //database version
    private static final int DATABASE_VERSION=1;

    private DBHelper ourHelper;

    private final Context ourContext;

    private SQLiteDatabase ourDatabase;




    /// constructor method

    public pillreminderDB(Context context)
    {
        ourContext=context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null ,DATABASE_VERSION);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlCode= "CREATE TABLE "+DATABASE_TABLE+" ("+
                    KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_Pillname+ " TEXT NOT NULL, "+
                    KEY_START+" TEXT NOT NULL, "+
                    KEY_Duration+" TEXT NOT NULL, "+
                    KEY_alarm+" TEXT NOT NULL);";
            db.execSQL(sqlCode);
            //onUpgrade(db,1,2);




        }


    }
    public pillreminderDB open() throws SQLException
    {
        ourHelper=new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    void close()
    {
        ourHelper.close();
    }

    public long createEntery(String name, String start , String duration, int alarmID)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_Pillname, name);
        cv.put(KEY_START, start);
        cv.put(KEY_Duration, duration);
        cv.put(KEY_alarm, alarmID);
        return ourDatabase.insert(DATABASE_TABLE, null,cv);
    }

    public String getData()
    {
        String [] colums=new String[]{KEY_Pillname, KEY_START, KEY_Duration,KEY_alarm};
        Cursor c=ourDatabase.query(DATABASE_TABLE,colums,null,null,null,null,null);
        String result=" ";
        int iRowID=c.getColumnIndex(KEY_ROWID);
        int iPillname=c.getColumnIndex(KEY_Pillname);
        int iStart=c.getColumnIndex(KEY_START);
        int iDuration=c.getColumnIndex(KEY_Duration);
        int iAlarm=c.getColumnIndex(KEY_alarm);
        for( c.moveToFirst();!c.isAfterLast() ;c.moveToNext() )
        {
            result= result + c.getString(iRowID)+": "+c.getString(iPillname)+": "
                    +c.getString(iStart)+": "+c.getString(iDuration)+": "+c.getString(iAlarm)+"\n";


        }

        c.close();
        return result;

    }

    public long deleteEntery(String rowID)
    {
        return ourDatabase.delete(DATABASE_TABLE,KEY_ROWID+"=?", new String[]{rowID});
    }

    public long updateEntery(String rowID, String pillname, String start, String duration, String alarmid)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_Pillname, pillname);
        cv.put(KEY_START, start);
        cv.put(KEY_Duration, duration);
        cv.put(KEY_alarm, alarmid);

        return ourDatabase.update(DATABASE_TABLE,cv,KEY_ROWID+"=?", new String[]{rowID});


    }



}
