//*********************************************************************************************
//**Program:      MyWeek                                                                     **
//**Programmer:   Timothy David Wiggins                                                      **
//**PURPOSE:      The purpose of this program is to help a home health professional manage   **
//**PURPOSE:      their weekly schedule of patients. The user will be able to add patients   **
//**PURPOSE:      to a database. From that database the user can schedule the patients for   **
//**PURPOSE:      any day of the week, or add notes to the patient. The app also lets the    **
//**PURPOSE:      know how many patients are in the database and how many are schedule for   **
//**PURPOSE:      the week.                                                                  **
//*********************************************************************************************

package com.example.myweek;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//*********************************************************************************************
//** Class:      MyWeekMainActivity                                                          **
//** Programmer: Timothy David Wiggins                                                       **
//** PURPOSE:    This class/activity provides access to the other activities of the app.     **
//** PURPOSE:    This activity contains four buttons: one to access the my week activity,    ** 
//** PURPOSE:    one for the manage clients activity, one for the totals activity and        **
//** PURPOSE:    finally a finished button that closes down the app.                         **
//*********************************************************************************************
public class MyWeekMainActivity extends Activity {
	
	Button btnMyWeekPageStarter, btnManageClientsStarter, btnTotal, btnMyDay;
	Spinner spinnerDay;
	TextView spinnerDaySelection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_week_main);
        
        //Setup Spinner
        spinnerDay = (Spinner)findViewById(R.id.spinnerDaySelectionMainActivity);
        getSpinnerDaySelection();
        
        //Setting up MyWeekPage Button
        btnMyWeekPageStarter    = (Button) findViewById(R.id.btnMyWeekPage);
        btnManageClientsStarter = (Button)findViewById(R.id.btnManageClients);
        btnTotal                = (Button)findViewById(R.id.btnTotals);
        btnMyDay                = (Button)findViewById(R.id.btnMyDayMainActivity);
        btnMyWeekPageStarter   .setOnClickListener(myButtonListener);
        btnManageClientsStarter.setOnClickListener(myButtonListener);
        btnTotal               .setOnClickListener(myButtonListener);
        btnMyDay               .setOnClickListener(myButtonListener);
        
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
				case R.id.btnMyWeekPage:	    Intent startWeekPage = new Intent(MyWeekMainActivity.this, myWeekPage.class);
    										    startActivity(startWeekPage);
    										    break;
    											
				case R.id.btnManageClients:	    Intent startManageClients = new Intent(MyWeekMainActivity.this, ManageClients.class);
											    startActivity(startManageClients);
											    break;
											
				case R.id.btnMyDayMainActivity: startMyDay();
												break;
											
				case R.id.btnTotals:  		    Intent startTotal = new Intent(MyWeekMainActivity.this, Totals.class);
											    startActivity(startTotal);
											    break;
			}
		}
	}; 
	
	//*******************************************************
	//** The method is to start the activity my day. If the**
	//** user has selected a day the activity starts, if   **
	//** not they will get a toast message.                **
	//*******************************************************
	private void startMyDay()
	{
		if(spinnerDaySelection.getText().equals("Select A Day"))
			Toast.makeText(this, "You must select a day first", Toast.LENGTH_LONG).show();
		else
		{
			Intent startMyDayActivity = new Intent(this, MyDay.class);
			startMyDayActivity.putExtra("activity", 1);
			startMyDayActivity.putExtra("day_name", spinnerDaySelection.getText().toString());
			startActivity(startMyDayActivity);
		}
	}
	
	//*******************************************************
	//** The method is to get the selection from the day   **
	//** spinner. The user selects a day in the spinner    **
	//** and it is placed in a text view for later use.    **
	//*******************************************************
	private void getSpinnerDaySelection()
	{
		spinnerDay.setOnItemSelectedListener(
		    new AdapterView.OnItemSelectedListener() 
		    {
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
	}//End of getSpinnerDaySelection
}//End of class
