package dbmanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper
{
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	String pathToDB = Constants.DATABASE_PATH + "/" + Constants.DATABASE_NAME;
	
	
	public DataBaseHelper(Context context)
	{
		super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
		this.myContext = context;
	}
			
	
	//Create the Database
	public SQLiteDatabase createDatabase() 
	{
		boolean dbExists = false;
		dbExists = checkIfDataBaseExists();
		myDataBase = getWritableDatabase();
		
		if(dbExists == false)
		{
			myDataBase.execSQL(Constants.DATABASE_CREATE);
			Log.d("HERE", "dfdf");
		}		
		return myDataBase;
	}//End of createDatabase
	
	//Copy database file to the DATABASE_NAME path
	private void copyDataBaseFromAssets() throws IOException
	{
		File myDirectory = new File(Constants.DATABASE_PATH);
		if(!myDirectory.exists())
		{
			myDirectory.mkdir();
		}
		
		InputStream myInput = myContext.getAssets().open(Constants.DATABASE_NAME);
		String outFileName = Constants.DATABASE_PATH + "/" + Constants.DATABASE_NAME;
		//Open the Empty Database as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0)
		{
			myOutput.write(buffer, 0, length);
		}
		
		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}//End of copyDataBaseFromAssets


	private boolean checkIfDataBaseExists() 
	{
		Log.d("In checkDatabaseExists", "Checking Database");
		
		//Checking to see if there is a database in the path
		File dbFile = myContext.getDatabasePath(Constants.DATABASE_NAME);
		return dbFile.exists();
	}//End of checkIfDataBaseExists


	//On Create for the DatabaseHelper class
	@Override
	public void onCreate(SQLiteDatabase db)
	{

	}//End of onCreate
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		throw new UnsupportedOperationException();
	}//End of onUpgrade
		
}//End of Class







