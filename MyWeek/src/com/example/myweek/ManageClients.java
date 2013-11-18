package com.example.myweek;

import java.util.ArrayList;
import dbmanager.Constants;
import dbmanager.PatientsDatabaseProvider;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ManageClients extends ListActivity
{
	private String[] namesArr;
	private ArrayList<String> selectedBoxes;
	private ArrayAdapter<String> myAdapter;
	private Button btnBack, btnAdd, btnDeleteAll, btnSchedule, btnAddNotes;
	private EditText patientName;
	private ListView lv;
	private Spinner spinner;
	private TextView spinnerSelection;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {         
       super.onCreate(savedInstanceState);    
       setContentView(R.layout.manage_clients);
       
       //Load the List View
       reloadList();

       selectedBoxes = new ArrayList<String>();
       patientName = (EditText)findViewById(R.id.enter_patient_name);
       lv = getListView();
       spinner = (Spinner)findViewById(R.id.spinnerManageClients);
       
       //Set up listener for the buttons
       btnBack      = (Button)findViewById(R.id.btnBackManageClients);
       btnAdd       = (Button)findViewById(R.id.btnAddManageClients);
       btnDeleteAll = (Button)findViewById(R.id.btnDeleteManageClients);
       btnSchedule  = (Button)findViewById(R.id.btnScheduleManageClients);
       btnAddNotes  = (Button)findViewById(R.id.btnAddNotesManageClientsPage);
       
       btnBack     .setOnClickListener(myButtonListener);
       btnAdd      .setOnClickListener(myButtonListener);
       btnDeleteAll.setOnClickListener(myButtonListener);
       btnSchedule .setOnClickListener(myButtonListener);
       btnAddNotes .setOnClickListener(myButtonListener);
    
       longClickHandler();
       setSpinnerSelection();
       getChecked();
       
    }//End of onCreate
    
	
	private OnClickListener myButtonListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
				case R.id.btnAddManageClients:			if(patientName.getText().length() == 0)
														{
														Toast.makeText(ManageClients.this, "Sorry you must enter name", Toast.LENGTH_SHORT).show();
														}									
														else
														{												
														saveRecordToDB();
														}
														break;
												
				case R.id.btnBackManageClients: 		Intent startMainActivity = new Intent(ManageClients.this, MyWeekMainActivity.class);
														startActivity(startMainActivity);
														break;
												
				case R.id.btnDeleteManageClients:		deleteAll();
														break;
												
				case R.id.btnScheduleManageClients: 	schedulePatients();
														break;
												
				case R.id.btnAddNotesManageClientsPage:	addNotes();
														break;
			}
		}


	};
	
	//Add notes to individual patients
	private void addNotes()
	{
		String[] selectedNames;
		selectedNames = (String[]) selectedBoxes.toArray(new String[selectedBoxes.size()]); 
		
		if(selectedNames.length > 1 || selectedNames.length == 0)
			Toast.makeText(this, "You must select ONE patient", Toast.LENGTH_SHORT).show();
		else 
		{
			String activity = "ManageClients.class";
			Intent startNotesPage = new Intent(ManageClients.this, Notes.class);
			startNotesPage.putExtra("name", selectedNames[0]);
			startNotesPage.putExtra("screen", activity);
			startNotesPage.putExtra("day", "RUBISH");
			startActivity(startNotesPage);
		}		
	}
	
	//Schedule Patients
	private void schedulePatients() 
	{
		 String sel = (String) spinnerSelection.getText();
		 
		 String[] selectedNames;
		 selectedNames = (String[]) selectedBoxes.toArray(new String[selectedBoxes.size()]);
		 
		 
		 if(sel.equals("Select A Day"))
		 {
			 Toast.makeText(this, "You must select a day first", Toast.LENGTH_SHORT).show();
		 }
		 else if(selectedBoxes.isEmpty())
		 {
			 Toast.makeText(this, "You must select Patients first", Toast.LENGTH_SHORT).show();
		 }
		 else
		 {
			 ContentValues value = new ContentValues();
			 if(sel.equals("Clear Schedule"))
			 {
				 value.put(Constants.COLUMN_MONDAY, 0);
			 	 value.put(Constants.COLUMN_TUESDAY, 0);
			 	 value.put(Constants.COLUMN_WEDNESDAY, 0);
			 	 value.put(Constants.COLUMN_THURSDAY, 0);
			 	 value.put(Constants.COLUMN_FRIDAY, 0);
			 }
			 else if(sel.equals("Monday"))
				 value.put(Constants.COLUMN_MONDAY, 1);
			 else if(sel.equals("Tuesday"))
				 value.put(Constants.COLUMN_TUESDAY, 1);
			 else if(sel.equals("Wednesday"))
				 value.put(Constants.COLUMN_WEDNESDAY, 1);
			 else if(sel.equals("Thursday"))
				 value.put(Constants.COLUMN_THURSDAY, 1);
			 else if(sel.equals("Friday"))
				 value.put(Constants.COLUMN_FRIDAY, 1);
			 
			 for(int x = 0; x < selectedNames.length; x++)
			 {
				 String whereClause = Constants.COLUMN_PATIENT_NAME + " = " + "'" + selectedNames[x] + "'";
				 getContentResolver().update(PatientsDatabaseProvider.TABLE_URI, 
						 value, whereClause, null);
			 }
			 Toast.makeText(this, "Patients scheduled", Toast.LENGTH_SHORT).show();

		 }//End of Condition

	}//End of  Schedule Patients
	
	//To get the selection from the spinner.
	private void setSpinnerSelection()
	{
		spinner.setOnItemSelectedListener(
		    new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View view,
						int arg2, long arg3) 
				{
					spinnerSelection = (TextView) view;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) 
				{
					//Nothing needed here
				}
		    }
		);
	}

	//Delete all patients in database
	private void deleteAll() 
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
						Toast.makeText(ManageClients.this, "All patients deleted", Toast.LENGTH_LONG).show();
						getContentResolver().delete(PatientsDatabaseProvider.TABLE_URI, null, null);
						reloadList();
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
	
	//Get all checked items
	private void getChecked()
	{
		   lv.setOnItemClickListener(new ListView.OnItemClickListener() 
	       {
	           @Override
	           public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                           long arg3)
	           {
	        	   if (lv.isItemChecked(arg2))
	               {
	                   String temp = lv.getItemAtPosition(arg2).toString();
	                   selectedBoxes.add(temp);
	               } 
	        	   else
	        		   selectedBoxes.remove(lv.getItemAtPosition(arg2).toString());
	           }
	       });
	}//End of getChecked
	
	//Setting up the long click listener
	private void longClickHandler()
	{
		       lv.setOnItemLongClickListener(new ListView.OnItemLongClickListener() 
		       {

		    	   public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                   int pos, long id) 
		    	   {
		    		   deleteRecordForLongClick(pos);
		    		   return true;
		    	   }
		       }); 
	}
	
	//For delete one record at a time with the Long Click
	private void deleteRecordForLongClick (int pos)
	{
		
		final int num = pos;
		//Show an alert dialog to confirm deletion
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setMessage("Are you sure you want to delete " + namesArr[num] + "?");

		//Set up two buttons. Right one is BUTTON_POSITIVE
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Delete patient",
			new DialogInterface.OnClickListener()
			{	
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					String column = Constants.COLUMN_PATIENT_NAME + " = " + "'" + namesArr[num] + "'";
					
					//Delete one record..
					Toast.makeText(getApplicationContext(), "Patient " + namesArr[num] + " deleted!", Toast.LENGTH_SHORT).show();
					getContentResolver().delete(
							PatientsDatabaseProvider.TABLE_URI, column, null);
					reloadList();
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
	}//END deleteRecord

	private void saveRecordToDB() 
	{
		//Set up a ContentValues object, simply a set of key/value pairs.
		ContentValues values = new ContentValues();
		values.put(Constants._id, 0);	//_id of 0 means a new record
		values.put(Constants.COLUMN_PATIENT_NAME, patientName.getText().toString());

		Uri recordUri = getContentResolver().insert(PatientsDatabaseProvider.TABLE_URI, values);

		long rowId = ContentUris.parseId(recordUri);
		
		//If the returned id is valid, clear out EditTexts and display a Toast message
		if (rowId >= 0)
		{
			//Tell the user
		    Toast.makeText(this, "New patient " + patientName.getText().toString() + " saved!", Toast.LENGTH_SHORT).show();
			patientName.setText("");
		
			reloadList();
		}
		else
		{
			Toast.makeText(this, "Record could not be created!", Toast.LENGTH_SHORT).show();
		}
	}//End of Save Records
	
	private void reloadList() 
	{
	    getNameColumns();
	    myAdapter = new ArrayAdapter<String>(this, R.layout.checkboxes_layout, namesArr);
        setListAdapter(myAdapter);
        	
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        getListView().setTextFilterEnabled(true);
	
	}

	private void getNameColumns()
	{
		String[] columns = new String[] { Constants.COLUMN_PATIENT_NAME };
		
		Cursor cursor = getContentResolver().query(PatientsDatabaseProvider.TABLE_URI, 
				columns, null, null, null);
				
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
		
		namesArr = (String[]) names.toArray(new String[names.size()]);
	}//End of getNameColumns
}
