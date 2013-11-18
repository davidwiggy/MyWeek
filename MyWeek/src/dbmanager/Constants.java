package dbmanager;

public class Constants {
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Patientsdb";
	public static final String TABLE_NAME = "PatientsTable";
	public static final String DATABASE_PATH = "data/data/com.example.myweek/databases";
	public static final String _id = "_id";
	public static final String COLUMN_PATIENT_NAME = "Name";
	public static final String COLUMN_MONDAY = "Monday";
	public static final String COLUMN_TUESDAY = "Tuesday";
	public static final String COLUMN_WEDNESDAY = "Wednesday";
	public static final String COLUMN_THURSDAY = "Thursday";
	public static final String COLUMN_FRIDAY = "Friday";
	public static final String COLUMN_NOTES = "Notes";
	
	public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
						+ TABLE_NAME          + " (" + _id + " integer primary key autoincrement, "
						+ COLUMN_PATIENT_NAME + " text, " 
						+ COLUMN_MONDAY       + " integer, "
						+ COLUMN_TUESDAY      + " integer, "
						+ COLUMN_WEDNESDAY    + " integer, "
						+ COLUMN_THURSDAY     + " integer, "
						+ COLUMN_FRIDAY       + " integer, "
						+ COLUMN_NOTES        + " text);";
	

}
