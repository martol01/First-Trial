package com.example.fragmenttest;

import java.io.FileOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction= fm.beginTransaction();
        //StartFragment startFragment = new StartFragment();
        //transaction.add(R.id.fragmentHolder, startFragment);
        transaction.commit();
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void onSelectFragment(View view)
    {
    	Fragment newFragment;
    	if(view ==findViewById(R.id.btnStartFragment))
    	{
    		newFragment = new StartFragment();
    	}
    	else if(view ==findViewById(R.id.btnFragment01))
    	{
    		newFragment = new Fragment01();
    	}
    	else if(view ==findViewById(R.id.btnFragment02))
    	{
    		newFragment = new Fragment02();
    	}
    	else {
    		newFragment= new StartFragment();
    	}
    	
    	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    	transaction.replace(R.id.fragmentHolder, newFragment);
    	transaction.addToBackStack(null);
    	transaction.commit();
    	
    }
    
    public void accessDataBase()
	{
    	//put values to the DataBase
		FeedReaddbHelper mHelper= new FeedReaddbHelper(getBaseContext());
		SQLiteDatabase db= mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		int id=1;
		String title="bull";
		String subtitle="hello";
	//	values.put(FeedEntry.COLUMN_NAME_ENTRY_ID,id);
		//values.put(FeedEntry.COLUMN_NAME_TITLE, title);
		//values.put(FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);
		long newRow;
		//newRow=db.insert(FeedEntry.TABLE_NAME, null, values);
	//READ FROM THE DATABASE
		SQLiteDatabase db1 = mHelper.getReadableDatabase();
		
		String[] projection= {
		//	FeedEntry._ID,FeedEntry.COLUMN_NAME_ENTRY_ID,FeedEntry.TABLE_NAME	
		};
		//String sortOrder=FeedEntry.COLUMN_NAME_TITLE + " DESC";
	//	Cursor c =db.query(FeedEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		//c.moveToFirst();
		//long itemID=c.getLong(c.getColumnIndexOrThrow(FeedEntry._ID));
		
		//DELETE DATA FROM DATABASE
		//String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		//String[] selectionArgs={String.valueOf(rowId)
	    // };
		//db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);
	
		
		//update values in database
		//int count = db1.update(FeedEntry.TABLE_NAME, values, selection, selectionArgs);
				
	} 
    
}
