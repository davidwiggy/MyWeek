package com.example.myweek;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ManageClients extends ListActivity
{
	static final String[] thu = new String[]
	{
		"John Schildt", "JavaScript Pollack", "Prentice Quiffffffffffffffffffffffff=gley",
		"Bronson Oriented", "Richard Bangs", "Pasquale Scaturro", "SLDFDSFDSF", "SDfdfsdfd", "SDFDSFDF1", "SDFSDFDFS", "SDFDSF"
	};
	
	private Button btnBack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.manage_clients);
       
       //ListView clients = (ListView)findViewById(R.id.lvManageClients);
       
       setListAdapter(new ArrayAdapter<String>
   			(this, R.layout.checkboxes_layout, thu));
       getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       getListView().setTextFilterEnabled(true);
       btnBack = (Button)findViewById(R.id.btnBackManageClients);
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
        			Intent startMainActivity = new Intent(ManageClients.this, MyWeekMainActivity.class);
        			startActivity(startMainActivity);
        		}
        	});
    }
	
}
