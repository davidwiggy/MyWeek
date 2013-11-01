package com.example.myweek;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PatientsDatabase extends ContentProvider 
{
	//Database related Constants
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Patientsdb";
	private static final String DATABASE_TABLE = "PatientsTable";
	
	//Database Columns
	public static final String COLUMN_PATIENT_NAME = "Name";
	public static final String COLUMN_DAY = "Day";
	public static final String COLUMN_NOTES = "Notes";
	
	private static final String DATABASE_CREATE = "CREATE TABLE "
			+ DATABASE_TABLE      + " (" 
			+ COLUMN_PATIENT_NAME + " TEXT PRIMARY KEY, " 
			+ COLUMN_DAY          + " INTEGER, " 
			+ COLUMN_NOTES        + "TEXT );";

	private SQLiteDatabase myDatabase;
	private DatabaseHelper myHelper;
	private Context myContext;
	
	//Constructor for PatientsDatabase
	public PatientsDatabase(Context c)
	{
		myContext = c;
	}
	
	public PatientsDatabase open()
	{
		myHelper = new DatabaseHelper(myContext);
		myDatabase = myHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		myHelper.close();
	}
	
	//DatabaseHelper class
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		public DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		//On Create for the DatabaseHelper class
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DATABASE_CREATE);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
