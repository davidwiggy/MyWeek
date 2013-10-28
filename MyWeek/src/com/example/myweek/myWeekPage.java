package com.example.myweek;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class myWeekPage extends ListActivity 
{
	static final String[] CHOICES = new String[]
	{
		"Read directions",
		"Highest Scores",
		"Play Game",
		"Quit"
	};
	static final String[] testing = new String[]
	{
		"David Wiggins", "Cassie Owens", "Debbie Downer"				
	};
	static final String[] wed = new String[]
	{
		"David Wiggins", "Ronnie Wiggins", "Ronnald WestBrock", "John Henry", "Westbrock Williams", "Shithead maGee"
	};
	static final String[] thu = new String[]
	{
		"John Schildt", "JavaScript Pollack", "Prentice Quiffffffffffffffffffffffff=gley",
		"Bronson Oriented", "Richard Bangs", "Pasquale Scaturro", "SLDFDSFDSF", "SDfdfsdfd", "SDFDSFDF1", "SDFSDFDFS", "SDFDSF"
	};
	
	Button btnBack;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myweekpage);
        
       ListView tuesdaylistview = (ListView)findViewById(R.id.tuesdaylist);
       ListView wednesdaylistview = (ListView)findViewById(R.id.wednesdaylist);
       ListView thursdaylistview = (ListView)findViewById(R.id.thursdaylist);
       ListView fridaylistview = (ListView)findViewById(R.id.fridaylist);
        
        //Set up ArrayAdaptor to display the current patients
        setListAdapter(new ArrayAdapter<String>
        	(this, R.layout.listviewsize, CHOICES));
        
       tuesdaylistview.setAdapter(new ArrayAdapter<String>(this,
        	   R.layout.listviewsize, testing));
       wednesdaylistview.setAdapter(new ArrayAdapter<String>(this,
    		   R.layout.listviewsize, wed));
       thursdaylistview.setAdapter(new ArrayAdapter<String>(this,
    		   R.layout.listviewsize, wed));
       fridaylistview.setAdapter(new ArrayAdapter<String>(this,
    		   R.layout.listviewsize, thu));
       
       //Setting up the Back btn
       btnBack = (Button)findViewById(R.id.btnBackMyWeekPage);
       setBtnBack();
        
    }//End of On Create

    
	private void setBtnBack()
    {
        btnBack.setOnClickListener
        (
        	new View.OnClickListener() 
        	{
        		public void onClick(View v)
        		{
        			Intent startMainActivity = new Intent(myWeekPage.this, MyWeekMainActivity.class);
        			startActivity(startMainActivity);
        		}
        	});
    }
    
}
