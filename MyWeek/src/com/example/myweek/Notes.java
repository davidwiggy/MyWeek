package com.example.myweek;

import java.util.ArrayList;
import dbmanager.Constants;
import dbmanager.PatientsDatabaseProvider;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//*********************************************************************************************
//** Class:      Notes                                                                       **
//** Programmer: Timothy David Wiggins                                                       **
//** PURPOSE:    This activity displays the notes of the selected patient. It allows the     **
//** PURPOSE:    user to see and update the notes on the patient. It loads a edit text with  ** 
//** PURPOSE:    the patients notes. It uses a Content Resolver to get the information from  **
//** PURPOSE:    a database.                                                                 **
//*********************************************************************************************
public class Notes extends Activity
{
	private String patientName, callingActivity, day;
	private TextView title;
	private Button btnBack, btnAddNote;
	private EditText notes;
	
	public void onCreate(Bundle savedInstanceState) 
    {         
       super.onCreate(savedInstanceState);    
       setContentView(R.layout.notespage);
       
       //Getting passed in variables
       Intent intent = getIntent();
       patientName = intent.getStringExtra("name");
       callingActivity = intent.getStringExtra("screen");
       day             = intent.getStringExtra("day");
       
       //Set up title
       title = (TextView)findViewById(R.id.txtlblCurrentPatientsNotes);
       title.setText(patientName);
       
       //Set up the Buttons
       btnBack    = (Button)findViewById(R.id.btnBackNotesPage);
       btnAddNote = (Button)findViewById(R.id.btnAddNotesPage);
       btnBack    .setOnClickListener(myButtonListener);
       btnAddNote .setOnClickListener(myButtonListener);
       
       //Set up EditText
       notes = (EditText)findViewById(R.id.etPatientNotes);
       loadEditText();

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
				case R.id.btnBackNotesPage:		startPreviousActivity();		
												break;
												
				case R.id.btnAddNotesPage:      addNote();
												break;
			}
		}
	}; 
	
	//*******************************************************
	//** The method is an onClick Listener for the buttons **
	//** It contains a switch statement to perform an      **
	//** action when one of the buttons is clicked.        **
	//*******************************************************
	private void addNote() 
	{
		ContentValues value = new ContentValues(); 
		value.put(Constants.COLUMN_NOTES, notes.getText().toString());
		String whereClause = Constants.COLUMN_PATIENT_NAME + " = " + "'" + patientName + "'";
		getContentResolver().update(PatientsDatabaseProvider.TABLE_URI, 
				value, whereClause, null);	
	
		loadEditText();
	}//End of add note
	
	//*******************************************************
	//** The method uses a Content Resolver to get info    **
	//** from the database. It then loads the info into a  **
	//** edit text.                                        **
	//*******************************************************
	private void loadEditText() 
	{
		String[] columns = new String[] { Constants.COLUMN_NOTES };
		String whereClause = Constants.COLUMN_PATIENT_NAME + " = " + "'" + patientName + "'";
		
		Cursor cursor = getContentResolver().query(PatientsDatabaseProvider.TABLE_URI, 
				columns, whereClause, null, null);
				
		ArrayList<String> note = new ArrayList<String>();
		if(cursor.moveToFirst())
		{
			do
			{
				int ct = 0;
				note.add(cursor.getString(ct));
				ct++;
			}while (cursor.moveToNext());
		}
		
		String[] temp;
		temp = (String[]) note.toArray(new String[note.size()]);
		notes.setText(temp[0]);
	}//End of loadEditText

	//*******************************************************
	//** This method starts the previous screen.           **
	//*******************************************************
	private void startPreviousActivity() 
	{
		if(callingActivity.equals("MyDay.class"))
		{
			Intent startPreviousPage = new Intent(Notes.this, MyDay.class);
			startPreviousPage.putExtra("day_name", day);
			startActivity(startPreviousPage);
		}
		else
		{
			Intent startPreviousPage = new Intent(Notes.this, ManageClients.class);
			startActivity(startPreviousPage);
		}
	}//End of Start Previous Activity
}//End of Notes Class
