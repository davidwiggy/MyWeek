package com.example.myweek;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MyWeekMainActivity extends Activity {
	
	Button btnMyWeekPageStarter;
	Button btnManageClientsStarter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_week_main);
        
        //Setting up MyWeekPage Button
        btnMyWeekPageStarter = (Button) findViewById(R.id.btnMyWeekPage);
        setMyWeekOnClickListener();
        
        //Setting up manageClients Button
        btnManageClientsStarter = (Button)findViewById(R.id.btnManageClients);
        setManageClientsOnClickListener();
        
    }//End of On Create
    
    private void setManageClientsOnClickListener() {
		btnManageClientsStarter.setOnClickListener
		(
			new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					Intent startManageClients = new Intent(MyWeekMainActivity.this, ManageClients.class);
					startActivity(startManageClients);
				}
		});
		
	}

	private void setMyWeekOnClickListener()
    {
        btnMyWeekPageStarter.setOnClickListener
        (
        	new View.OnClickListener() 
        	{
        		public void onClick(View v)
        		{
        			Intent startWeekPage = new Intent(MyWeekMainActivity.this, myWeekPage.class);
        			startActivity(startWeekPage);
        		}
        	});
    }

    
}
