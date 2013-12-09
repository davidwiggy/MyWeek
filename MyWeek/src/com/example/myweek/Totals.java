package com.example.myweek;

import dbmanager.Constants;
import dbmanager.PatientsDatabaseProvider;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//*********************************************************************************************
//** Class:      Totals                                                                      **
//** Programmer: Timothy David Wiggins                                                       **
//** PURPOSE:    This activity displays the total amount of patients in the database. It     **
//** PURPOSE:    also displays the total amount of patients scheduled for the week. It uses  ** 
//** PURPOSE:    a Content Resolver and a cursor to get the information.                     **
//*********************************************************************************************
public class Totals extends Activity
{
	private TextView databaseTotal, scheduleTotal;
	private Button btnBack;
	
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.totalpage);
	    
	    //Get Totals
	    int totalPatients = getDatabaseTotal();
	    int totalsch      = getScheduleTotal();
	    
	    databaseTotal = (TextView)findViewById(R.id.tvDatabaseTotal);
	    scheduleTotal = (TextView)findViewById(R.id.tvTotalPatientsSchedule);
	    databaseTotal.setText("The total amount of patients in the database is: " + totalPatients); 
	    scheduleTotal.setText("The total amount on your schedule is: " + totalsch);
	    
	    //Set up the button
	    btnBack = (Button)findViewById(R.id.btnBackTotalPage);
	    btnBack.setOnClickListener(myButtonListener);    
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
				case R.id.btnBackTotalPage: Intent back = new Intent(Totals.this, MyWeekMainActivity.class);
											startActivity(back);
											break;
			}
		}
	}; 
	
	//*******************************************************
	//** This method gets the total patients scheduled. It **
	//** uses a Content Resolver and a cursor and adds it  **
	//** to a total count and then returns the count.      **
	//*******************************************************
    private int getScheduleTotal()
    {
    	Cursor cursor;
    	String whereClause = Constants.COLUMN_MONDAY + " = " + 1;
    	int count = 0;
    	
    	for(int x = 0; x < 5; x++)
    	{
    		cursor = getContentResolver().query(PatientsDatabaseProvider.TABLE_URI, 
    			null, whereClause, null, null);
    		switch(x)
    		{
    			case 0:	count += cursor.getCount();
    					whereClause = Constants.COLUMN_TUESDAY + " = " + 1;
    					break;
    			case 1: count += cursor.getCount();
    					whereClause = Constants.COLUMN_WEDNESDAY + " = " + 1;
    					break;
    			case 2: count += cursor.getCount();
    					whereClause = Constants.COLUMN_THURSDAY + " = " + 1;
    					break;
    			case 3: count += cursor.getCount();
    					whereClause = Constants.COLUMN_FRIDAY + " = " + 1;
    					break;
    			case 4: count += cursor.getCount();
    					break;
    		}	
    	}
    	
    	return count;
    }//End of getCursor
	
	//*******************************************************
	//** This method gets the total patients in the data-  **
	//** base and then returns the count.                  **
	//*******************************************************
	private int getDatabaseTotal()
	{
		Cursor cursor;
		cursor = getContentResolver().query(PatientsDatabaseProvider.TABLE_URI, null, null, null, null);
		int count = cursor.getCount();
		
		return count;
	}
}
