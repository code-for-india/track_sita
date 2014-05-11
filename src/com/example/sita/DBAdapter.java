package com.example.sita;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	private static final String DATABASE_NAME = "sitaDB";
	
	public static final String KEY_SCHOOL_ID = "school_id";
	public static final String KEY_SCHOOL_NAME = "school_name";
	public static final String KEY_SCHOOL_LAT = "school_lat";
	public static final String KEY_SCHOOL_LONG = "school_long";
	
	private static final String TABLE_SCHOOL = "schoolTable";
	
	
	
	
	
	
	public DBAdapter (Context context)
	{
		ourContext = context;
	}
	
	private static class DbHelper extends SQLiteOpenHelper
	{
		
		public DbHelper(Context context)
		{
			super(context,DATABASE_NAME, null, DATABASE_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + TABLE_SCHOOL + " ( " +
					KEY_SCHOOL_ID + " INTEGER PRIMARY KEY, " +
					KEY_SCHOOL_NAME + " VARCHAR(1000), " +
					KEY_SCHOOL_LAT + " REAL, "+
					KEY_SCHOOL_LONG + " REAL);"
					);
			//String sql = "INSERT INTO "+ TABLE_SCHOOL + " ("+ KEY_SCHOOL_ID +", "+ KEY_SCHOOL_NAME + ", " + KEY_SCHOOL_LAT +", " + KEY_SCHOOL_LONG +") values (1001,'Google Test School',12.9959,77.6636);";
			
			// db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL);
			onCreate(db);
			
		}
		
	}
	
	public DBAdapter open() throws SQLException
	{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		ourHelper.close();
	}
	
	public void fillDB()	// this has finally go into onCreate method! // inefficient for production!
	{
		ourDatabase.delete(TABLE_SCHOOL, null, null);
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_SCHOOL_ID, 1001);
		cv.put(KEY_SCHOOL_NAME, "Google Bangalore");
		cv.put(KEY_SCHOOL_LAT, 12.9959 );
		cv.put(KEY_SCHOOL_LONG, 77.6636);
		ourDatabase.insert(TABLE_SCHOOL, null,cv);
		
		cv.put(KEY_SCHOOL_ID, 1002);
		cv.put(KEY_SCHOOL_NAME, "Chinmaya Vidyalaya");
		cv.put(KEY_SCHOOL_LAT, 23.666468 );
		cv.put(KEY_SCHOOL_LONG, 86.168659);
		ourDatabase.insert(TABLE_SCHOOL, null,cv);
		
		cv.put(KEY_SCHOOL_ID, 1003);
		cv.put(KEY_SCHOOL_NAME, "DPS Electronic City");
		cv.put(KEY_SCHOOL_LAT, 12.843373 );
		cv.put(KEY_SCHOOL_LONG, 77.635113);
		ourDatabase.insert(TABLE_SCHOOL, null,cv);
		
		cv.put(KEY_SCHOOL_ID, 1004);
		cv.put(KEY_SCHOOL_NAME, "St German School");
		cv.put(KEY_SCHOOL_LAT, 12.993722 );
		cv.put(KEY_SCHOOL_LONG, 77.609776);
		ourDatabase.insert(TABLE_SCHOOL, null,cv);
		
		cv.put(KEY_SCHOOL_ID, 1005);
		cv.put(KEY_SCHOOL_NAME, "Government Middle School");
		cv.put(KEY_SCHOOL_LAT, 12.994665 );
		cv.put(KEY_SCHOOL_LONG, 77.6638908);
		ourDatabase.insert(TABLE_SCHOOL, null,cv);
		
		
		
		
		
		
		
		
	}
	
	public ArrayList<SchoolIdName> getNearBySchools(float actualLat, float actualLong)
	{
		String[] columns = { KEY_SCHOOL_ID, KEY_SCHOOL_NAME, KEY_SCHOOL_LAT, KEY_SCHOOL_LONG};
		
		
		Cursor c = ourDatabase.query(TABLE_SCHOOL, columns, "((("+ KEY_SCHOOL_LAT + " < " +	Float.toString((float) (actualLat +0.01)) + ") " +
															   " AND (" + KEY_SCHOOL_LAT + " > " + Float.toString((float) (actualLat -0.01))+"))" +
															   " AND (("+ KEY_SCHOOL_LONG + " < " + Float.toString((float) (actualLong +0.01)) + ") " +
															   "AND (" + KEY_SCHOOL_LONG + " > " + Float.toString((float) (actualLong -0.01))+")))"  , null, null, null, null);
		
		
		//Cursor c = ourDatabase.query(TABLE_SCHOOL, columns,null , null, null, null, null);
		 ArrayList<SchoolIdName> result = new  ArrayList<SchoolIdName>();
		int iSchoolID = c.getColumnIndex(KEY_SCHOOL_ID);
		int iSchoolName = c.getColumnIndex(KEY_SCHOOL_NAME);
		//int iSchoolLat = c.getColumnIndex(KEY_SCHOOL_LAT);
		//int iSchoolLong = c.getColumnIndex(KEY_SCHOOL_LONG);
		
		
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			SchoolIdName temp = new SchoolIdName(c.getInt(iSchoolID), c.getString(iSchoolName));
			result.add(temp);
			
		}
		return result;
			
		
	}

}
