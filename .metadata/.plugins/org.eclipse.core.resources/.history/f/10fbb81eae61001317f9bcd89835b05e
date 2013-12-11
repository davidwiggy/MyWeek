package com.example.myweek;

import java.util.ArrayList;

import dbmanager.Constants;
import dbmanager.PatientsDatabaseProvider;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

//*********************************************************************************************
//** Class:      myWeekPage                                                                       **
//** Programmer: Timothy David Wiggins                                                       **
//** PURPOSE:    This activity displays the weeks schedule. It has five list views side by   **
//** PURPOSE:    side. From this activity the user can clear the whole weeks schedule. Or go ** 
//** PURPOSE:    to any day of the week, by selecting a day then press the go to day button. **
//** PURPOSE:    This class load the patient names that are scheduled into arrays and uses   **
//** PURPOSE:    those arrays to load the list views.                                        **
//*********************************************************************************************
public class myWeekPage extends ListActivity 
{
	private Button btnBack, btnMyDay, btnClear;
	private Spinner spinnerSelection;
	private String spinnerText;
	private Cursor cursor;
	private ListView tuesdaylistview, wednesdaylistview, thursdaylistview, fridaylistview;
	
	private String[] monday, tuesday, wednesday, thursday, friday;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.myweekpage);

       tuesdaylistview = (ListView)findViewById(R.id.tuesdaylist);
       wednesdaylistview = (ListView)findViewById(R.id.wednesdaylist);
       thursdaylistview = (ListView)findViewById(R.id.thursdaylist);
       fridaylistview = (ListView)findViewById(R.id.fridaylist);

        //Set up ArrayAdaptor to display the current patients
	    setListViews();
       
	   //Setting up the buttons
       btnBack = (Button)findViewById(R.id.btnBackMyWeekPage);
       btnMyDay = (Button)findViewById(R.id.btnGoToDayMyWeekPage);
       btnClear = (Button)findViewById(R.id.btnClearScheduleMyWeek);
       btnBack.setOnClickListener(myButtonListener);
       btnMyDay.setOnClickListener(myButtonListener);
       btnClear.setOnClickListener(myButtonListener);
       
       //Setting up the spinner
       spinnerSelection = (Spinner)findViewById(R.id.spinnerDaySelectionMyWeekPage);
    }//End of On Create

	//*******************************************************
	//** The method is an onClick Listener for the buttons **
	//** It contains a switch statement to perform an      **
	//** action when one of the buttons is clicked.        **
	//*******************************************************
	private OnClickListener myButtonListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
				case R.id.btnBackMyWeekPage:		Intent startMainActivity = new Intent(myWeekPage.this, MyWeekMainActivity.class);
	    											startActivity(startMainActivity);
	    											break;
				
				case R.id.btnGoToDayMyWeekPage: 	spinnerText = spinnerSelection.getSelectedItem().toString();
	    											if(spinnerText.equals("Select A Day") || spinnerText.equals("Clear Schedule"))
	    												Toast.makeText(myWeekPage.this, "You must select a day first.", Toast.LENGTH_LONG).show();
	    											else
	    											{
	    												Intent startMyDayActivity = new Intent(myWeekPage.this, MyDay.class);
	    												startMyDayActivity.putExtra("day_name", spinnerText);
	    												startActivity(startMyDayActivity);
    											    }
	    											break;
	    											
				case R.id.btnClearScheduleMyWeek:	clearSchedule();
			}
		}
	};
    
	//*******************************************************
	//** The method updates the values in the database. To **
	//** clear the schedule. Once the schedule is cleared. **
	//** It reloads the listViews.                         **
	//*******************************************************
	private void clearSchedule() 
	{
		//Show an alert dialog to confirm deletion
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setMessage("Are you sure you would like to delete ALL patients?");
		
		//Set up the buttons. Right is Button_Postive
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Delete All",
				new DialogInterface.OnClickListener() 
				{	
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
						 ContentValues value = new ContentValues();
						 value.put(Constants.COLUMN_MONDAY, 0);
					 	 value.put(Constants.COLUMN_TUESDAY, 0);
					 	 value.put(Constants.COLUMN_WEDNESDAY, 0);
					 	 value.put(Constants.COLUMN_THURSDAY, 0);
					 	 value.put(Constants.COLUMN_FRIDAY, 0);
					 	 
						getContentResolver().update(PatientsDatabaseProvider.TABLE_URI, 
								value, null, null);
						
						setListViews();
					}
				});
		
		//This is the left button in the dialog box
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
			new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					//Do nothing. Dialog will simply disappear.
				}
			});
		dialog.show();
	}
	
	//*******************************************************
	//** The method loads the list views from the arrays.  **
	//** It uses five list views that are filled.          **
	//*******************************************************
	private void setListViews()
	{
		getCursor();
		setListAdapter(new ArrayAdapter<String>
	       (this, R.layout.listviewsize, monday));
	    tuesdaylistview.setAdapter(new ArrayAdapter<String>(this,
       	   R.layout.listviewsize, tuesday));
	    wednesdaylistview.setAdapter(new ArrayAdapter<String>(this,
   		   R.layout.listviewsize, wednesday));
	    thursdaylistview.setAdapter(new ArrayAdapter<String>(this,
   		   R.layout.listviewsize, thursday));
	    fridaylistview.setAdapter(new ArrayAdapter<String>(this,
   		   R.layout.listviewsize, friday));
	}
	
	//*******************************************************
	//** This method uses a ContentResolver to load the    **
	//** cursor with the schedule for each day. It calls   **
	//** another method to load the arrays once the cursor **
	//** is set.                                           **
	//*******************************************************
    private void getCursor()
    {
    	String[] columns = new String[] { Constants.COLUMN_PATIENT_NAME };
    	
    	String whereClause = Constants.COLUMN_MONDAY + " = " + 1;
    	
    	for(int x = 0; x < 5; x++)
    	{
    		cursor = getContentResolver().query(PatientsDatabaseProvider.TABLE_URI, 
    			columns, whereClause, null, null);
    		
    		switch(x)
    		{
    			case 0:	loadArrays(x);
    					whereClause = Constants.COLUMN_TUESDAY + " = " + 1;
    					break;
    			case 1: loadArrays(x);
    					whereClause = Constants.COLUMN_WEDNESDAY + " = " + 1;
    					break;
    			case 2: loadArrays(x);
    					whereClause = Constants.COLUMN_THURSDAY + " = " + 1;
    					break;
    			case 3: loadArrays(x);
    					whereClause = Constants.COLUMN_FRIDAY + " = " + 1;
    					break;
    			case 4: loadArrays(x);
    					break;
    		}	
    	}
    }//End of getCursor

	//*******************************************************
	//** This method loads the arrays from the cursor. It  **
	//** is called each time the cursor is set. First it   **
	//** loads the names into a ArrayList and then into the**
    //** String arrays.                                    **
	//*******************************************************
	private void loadArrays(int x)
	{			
		ArrayList<String> names = new ArrayList<String>();
		if(cursor.moveToFirst())
		{
			do
			{
				int ct = 0;
				names.add(cursor.getString(ct));
				ct++;
			}while (cursor.moveToNext());
		}

		switch(x)
		{
			case 0:	monday = (String[]) names.toArray(new String[names.size()]);
					names.clear();
					break;
			case 1: tuesday = (String[]) names.toArray(new String[names.size()]);
					names.clear();
					break;
			case 2: wednesday = (String[]) names.toArray(new String[names.size()]);
					names.clear();
					break;
			case 3: thursday = (String[]) names.toArray(new String[names.size()]);
					names.clear();
					break;
			case 4: friday = (String[]) names.toArray(new String[names.size()]);
					names.clear();
					break;
		}
	}//End of loadArrays
}//End of Class
