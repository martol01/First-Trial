package com.example.fragmenttest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment02 extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		SharedPreferences sharedPrefs=getActivity().getPreferences(Context.MODE_PRIVATE);
	   int defaultValue=12345;
	   long highScore=sharedPrefs.getInt("highScore", defaultValue);
	   Log.d("DEBUG","the read highScore is "+highScore);
	   
	   FileInputStream inputStream;
	    String dataToRead="";
	    try
	    {
	    	inputStream=getActivity().openFileInput("text.txt");
	    	InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
	    	BufferedReader bufRead= new BufferedReader(inputStreamReader);
	    	String fileContent=bufRead.readLine();
	    	while(fileContent!=null)
	    	{
	    		dataToRead+=fileContent;
	    		fileContent=bufRead.readLine();
	    	}
	    	inputStream.close();
	    	Log.d("DEBUG","the data that was read is: "+dataToRead);
	    	Log.d("DEBUG","free space is: "+getActivity().getFilesDir().getFreeSpace());
	    	Log.d("DEBUG","total space is: "+getActivity().getFilesDir().getTotalSpace());
	    	//getActivity().deleteFile("text.txt"); delete a file
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
		
		return inflater.inflate(R.layout.fragment_02,container,false);
	}
	

}
