package com.example.myweek;

import java.util.ArrayList;

import dbmanager.Constants;
import dbmanager.PatientsDatabaseProvider;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi")
public class MyDay extends ListActivity 
				implements LoaderCallbacks<Cursor>
{
	private ArrayList<String> selectedBoxes;
	private SimpleCursorAdapter myAdapter, spinnerAdapter;
	private Spinner spinnerDay, spinnerPatient;
	private Button btnBack, btnAdd, btnMove, btnChangeDay;
	private String day;
	private ListView lview;
	private TextView title, spinnerPatientSelection, spinnerDaySelection;
	private Cursor spinCur;
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.mydaypage);
       selectedBoxes = new ArrayList<String>();
       
       //Getting passed in variables
       Intent intent = getIntent();
       day = intent.getStringExtra("day_name");
       
       //Setting up the Title TextView
       title = (TextView)findViewById(R.id.titleDay);
       title.setText(day);
       
       //Setting up the Spinner with a list from the database
       spinnerPatient = (Spinner)findViewById(R.id.spinnerPatientSelectionMyDay);
       spinnerDay     = (Spinner)findViewById(R.id.spinnerDaySelectionMyDay);
       setUpDaySpinner();
       setSpinnerPatientSelection();
       setSpinnerDaySelection();
       
       //Set up checkBoxes
       lview = getListView();
       getChecked();
       
       String[] from = new String[] { Constants.COLUMN_PATIENT_NAME };
       
       int[] to = new int[] { R.id.checkbox1 };
       
       myAdapter = new SimpleCursorAdapter(this, R.layout.checkboxes_layout, null, from, to, 0);
       setListAdapter(myAdapter);
       
       getLoaderManager().initLoader(1, null, this);
       registerForContextMenu(getListView());
       getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       getListView().setTextFilterEnabled(true);
       
       
       btnBack      = (Button)findViewById(R.id.btnBackMyDay);
       btnAdd       = (Button)findViewById(R.id.btnAddMyDay);
       btnMove      = (Button)findViewById(R.id.btnMovePatientsMyDay);
       btnChangeDay = (Button)findViewById(R.id.btnChangeDayPatientsMyDay);
       
       btnBack     .setOnClickListener(myButtonListener);
       btnAdd      .setOnClickListener(myButtonListener);
       btnMove     .setOnClickListener(myButtonListener);
       btnChangeDay.setOnClickListener(myButtonListener);
       

   }//End of On Create
    
	private void setUpDaySpinner() 
	{
		String[] projection = new String[] { Constants._id, Constants.COLUMN_PATIENT_NAME };
		
		spinCur = getContentResolver().query(PatientsDatabaseProvider.TABLE_URI, 
				projection, null, null, null);
		
		String[] columns = new String[] { Constants.COLUMN_PATIENT_NAME };
		int[] to = new int[] { android.R.id.text1, R.id.spinnerPatientSelectionMyDay };
		
		spinnerAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item,
							spinCur, columns, to, 0);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerPatient.setAdapter(spinnerAdapter);
	} 

	private OnClickListener myButtonListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
				case R.id.btnBackMyDay:				 Intent startMyWeekPageActivity = new Intent(MyDay.this, myWeekPage.class);
    												 startActivity(startMyWeekPageActivity);
    												 break;
    												 
				case R.id.btnAddMyDay:			 	 addPatient();
													 break;
													 
				case R.id.btnMovePatientsMyDay: 	 movePatients();
													 break;
													 
				case R.id.btnChangeDayPatientsMyDay: changeDay();
													 break;
			}
		}
	}; 

	
	//Method to change the day 
	private void changeDay() 
	{
		if(spinnerDaySelection.getText().equals("Select A Day") || spinnerDaySelection.getText().equals("Clear Schedule"))
			Toast.makeText(this, "You must select a day first", Toast.LENGTH_SHORT).show();
		else
		{
			Intent startMyDayActivity = new Intent(this, MyDay.class);
			startMyDayActivity.putExtra("day_name", spinnerDaySelection.getText().toString());
			startActivity(startMyDayActivity);
		}
			
	}
	
	
	//To add Patients to the day.
	@SuppressLint("NewApi")
	private void addPatient() 
	{
		String patientSelection = spinnerPatientSelection.getText().toString();
		
		if(patientSelection.isEmpty())
			Toast.makeText(this, "You must select a Patient", Toast.LENGTH_SHORT).show();
		
		 ContentValues value = new ContentValues();
		 if(day.equals("Monday"))
			 value.put(Constants.COLUMN_MONDAY, 1);
		 else if(day.equals("Tuesday"))
			 value.put(Constants.COLUMN_TUESDAY, 1);
		 else if(day.equals("Wednesday"))
			 value.put(Constants.COLUMN_WEDNESDAY, 1);
		 else if(day.equals("Thursday"))
			 value.put(Constants.COLUMN_THURSDAY, 1);
		 else if(day.equals("Friday"))
			 value.put(Constants.COLUMN_FRIDAY, 1);
		 
		 String whereClause = Constants.COLUMN_PATIENT_NAME + " = " + "'" + patientSelection + "'";
		 getContentResolver().update(PatientsDatabaseProvider.TABLE_URI, 
				 value, whereClause, null);
	
		 Toast.makeText(this, "Patients scheduled", Toast.LENGTH_SHORT).show();
		 
	}
	
	private void movePatients() 
	{
		 String selectionDay = (String) spinnerDaySelection.getText();
		 
		 String[] selectedNames;
		 selectedNames = (String[]) selectedBoxes.toArray(new String[selectedBoxes.size()]);
		 
		 if(selectionDay.equals("Select A Day"))
		 {
			 Toast.makeText(this, "You must select a day first", Toast.LENGTH_SHORT).show();
		 }
		 else if(selectedBoxes.isEmpty())
		 {
			 Toast.makeText(this, "You must select Patients first", Toast.LENGTH_SHORT).show();
		 }
		 else
		 {
			 String column = getDayColumn();
		
			 ContentValues value = new ContentValues();
			 if(selectionDay.equals("Monday"))
			 {
				 value.put(Constants.COLUMN_MONDAY, 1);
				 value.put(column, 0);
			 }	 
			 else if(selectionDay.equals("Tuesday"))
			 {
				 Toast.makeText(this, "IN CAON", Toast.LENGTH_SHORT).show();
				 value.put(Constants.COLUMN_TUESDAY, 1);
				 value.put(column, 0);
			 }
			 else if(selectionDay.equals("Wednesday"))
			 {
				 value.put(Constants.COLUMN_WEDNESDAY, 1);
				 value.put(column, 0);
			 }
			 else if(selectionDay.equals("Thursday"))
			 {
				 value.put(Constants.COLUMN_THURSDAY, 1);
				 value.put(column, 0);
			 }
			 else if(selectionDay.equals("Friday"))
			 {
				 value.put(Constants.COLUMN_FRIDAY, 1);
				 value.put(column, 0);
			 }
			 
			 //For loop to update all selected names.
			 for(int x = 0; x < selectedNames.length; x++)
			 {
				 String whereClause = Constants.COLUMN_PATIENT_NAME + " = " + "'" + selectedNames[x] + "'";
				 getContentResolver().update(PatientsDatabaseProvider.TABLE_URI, 
						 value, whereClause, null);
			 }
			 Toast.makeText(this, "Patients scheduled", Toast.LENGTH_SHORT).show();

		 }//End of Condition

	}//End of  Schedule Patients
	
	
	//To get the selection from the spinner for Patient.
	private void setSpinnerPatientSelection()
	{
		spinnerPatient.setOnItemSelectedListener(
		    new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View view,
						int arg2, long arg3) 
				{
					spinnerPatientSelection = (TextView) view;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) 
				{
					//Nothing needed here
				}
		    }
		);
	}
	
	//To get the selection from the spinner for Day.
	
	private void setSpinnerDaySelection()
	{
		spinnerDay.setOnItemSelectedListener(
		    new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View view,
						int arg2, long arg3) 
				{
					spinnerDaySelection = (TextView) view;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) 
				{
					//Nothing needed here
				}
		    }
		);
	}
	
	//Get all checked items
	private void getChecked()
	{
		   lview.setOnItemClickListener(new ListView.OnItemClickListener() 
	       {
	           @Override
	           public void onItemClick(AdapterView<?> parent, View view, int position,
	                           long id)
	           {
	        	   if (lview.isItemChecked(position))
	               {
	        		   String item = ((TextView) view).getText().toString();
	                   selectedBoxes.add(item);
	               } 
	        	   else
	        	   {
	        		   
	        		   String item = ((TextView) view).getText().toString();
	        		   selectedBoxes.remove(item);
	        	   }
	           }
	       });
	}//End of getChecked

	private String getDayColumn()
	{
		String column; 
		if(day.equals("Monday"))
			 column = Constants.COLUMN_MONDAY;
		 else if(day.equals("Tuesday"))
			 column = Constants.COLUMN_TUESDAY;
		 else if(day.equals("Wednesday"))
			 column = Constants.COLUMN_WEDNESDAY;
		 else if(day.equals("Thursday"))
			 column = Constants.COLUMN_THURSDAY;
		 else
			 column = Constants.COLUMN_FRIDAY;
		
		 return column;
	}
	
    @SuppressLint("NewApi")
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
    	
        String whereClause = getDayColumn();
            
            CursorLoader cursorLoader = new CursorLoader(this,
                            PatientsDatabaseProvider.TABLE_URI, null, whereClause, null, null);
            return cursorLoader;
    }

    @SuppressLint("NewApi")
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor myCursor) 
    {
            myAdapter.swapCursor(myCursor);
            
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) 
    {
            myAdapter.swapCursor(null);
            
    }
		

}
	