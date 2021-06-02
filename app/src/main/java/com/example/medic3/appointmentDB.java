package com.example.medic3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class appointmentDB {

    // columns of the table
    public static final String KEY_ROWID="_id";
    public static final String KEY_region="region_name";
    public static final String KEY_center="center_name";
    public static final String KEY_doctor="_doctor";
    public static final String KEY_Date="_Date";
    public static final String KEY_Time="_Time";

    // database name
    private static final String DATABASE_NAME="appontmentDB";

    // tablename
    private static final String DATABASE_TABLE="appointmentTable";

    //database version
    private static final int DATABASE_VERSION=1;

    private appointmentDB.DBHelper ourHelper;

    private final Context ourContext;

    private SQLiteDatabase ourDatabase;

    /// constructor method

    public appointmentDB(Context context)
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
                    KEY_region+ " TEXT NOT NULL, "+
                    KEY_center+" TEXT NOT NULL, "+
                    KEY_doctor+" TEXT NOT NULL, "+
                    KEY_Date+" TEXT NOT NULL, "+
                    KEY_Time+" TEXT NOT NULL);";
            db.execSQL(sqlCode);




        }


    }

    public appointmentDB open() throws SQLException
    {
        ourHelper=new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    void close()
    {
        ourHelper.close();
    }

    public long createEntery(String region, String center , String doctor, String date, String time )
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_region, region);
        cv.put(KEY_center, center);
        cv.put(KEY_doctor, doctor);
        cv.put(KEY_Date, date);
        cv.put(KEY_Time, time);
        return ourDatabase.insert(DATABASE_TABLE, null,cv);

    }

    public String getData()
    {
        String [] colums=new String[]{KEY_ROWID,KEY_region, KEY_center, KEY_doctor,KEY_Date, KEY_Time};
        Cursor c=ourDatabase.query(DATABASE_TABLE,colums,null,null,null,null,null);
        String result=" ";
        int iRowID=c.getColumnIndex(KEY_ROWID);
        int iRegion=c.getColumnIndex(KEY_region);
        int iCenter=c.getColumnIndex(KEY_center);
        int iDoctor=c.getColumnIndex(KEY_doctor);
        int iDate=c.getColumnIndex(KEY_Date);
        int iTime=c.getColumnIndex(KEY_Time);
        for( c.moveToFirst();!c.isAfterLast() ;c.moveToNext() )
        {
            result= result + c.getString(iRowID)+":"+c.getString(iRegion)+":"
                    +c.getString(iCenter)+":"+c.getString(iDoctor)+":"+c.getString(iDate)+":"+c.getString(iTime)+"\n";


        }

        c.close();
        return result;

    }

    public void cleardb()
    {
        ourDatabase.execSQL("delete from "+ DATABASE_TABLE);
    }

    public long deleteEntery(String rowID)
    {
        return ourDatabase.delete(DATABASE_TABLE,KEY_ROWID+"=?", new String[]{rowID});
    }

    public long updateEntery(String rowID, String region, String center , String doctor, String date, String time)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_region, region);
        cv.put(KEY_center, center);
        cv.put(KEY_doctor, doctor);
        cv.put(KEY_Date, date);
        cv.put(KEY_Time, time);

        return ourDatabase.update(DATABASE_TABLE,cv,KEY_ROWID+"=?", new String[]{rowID});


    }



}
