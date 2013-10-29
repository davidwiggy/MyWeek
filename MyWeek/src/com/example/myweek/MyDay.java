package com.example.myweek;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MyDay extends ListActivity
{
	static final String[] thu = new String[]
	{
		"John Schildt", "JavaScript Pollack", "Prentice Quiffffffffffffffffffffffff=gley",
		"Bronson Oriented", "Richard Bangs", "Pasquale Scaturro", "SLDFDSFDSF", "SDfdfsdfd", "SDFDSFDF1", "SDFSDFDFS", "SDFDSF"
	};
	
	private Button btnBack;
	private String day;
	private TextView title;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.mydaypage);
       
       //Getting passed in variables
       Intent intent = getIntent();
       day = intent.getStringExtra("day_name");
       
       //Setting up the Title TextView
       title = (TextView)findViewById(R.id.titleDay);
       title.setText(day);
       
       //ListView clients = (ListView)findViewById(R.id.lvManageClients);
       
       setListAdapter(new ArrayAdapter<String>
   			(this, R.layout.checkboxes_layout, thu));
       getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       getListView().setTextFilterEnabled(true);
       btnBack = (Button)findViewById(R.id.btnBackMyDay);
       setBtnBack();

   }
    
	private void setBtnBack()
    {
        btnBack.setOnClickListener
        (
        	new View.OnClickListener() 
        	{
       		public void onClick(View v)
        		{
        			Intent startMyWeekPageActivity = new Intent(MyDay.this, myWeekPage.class);
        			startActivity(startMyWeekPageActivity);
        		}
        	});
    }
}
	