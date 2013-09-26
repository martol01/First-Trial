package com.example.fragmenttest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment01 extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Context context = getActivity();
		SharedPreferences sharedPrefs = context.getSharedPreferences("com.example.myapp.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);//for multiple preferences files only
		//OR
		SharedPreferences sharedPRefs=getActivity().getPreferences(Context.MODE_PRIVATE);//for one preferences file only
		SharedPreferences.Editor editor= sharedPRefs.edit();
		editor.putInt("highScore",1000);
		editor.commit();
		Log.d("DEBUG","wrote to preferences");
		
		String filename="text.txt";
		String data="Hello my saved data";
		FileOutputStream outputStream;
		//FileInputStream inputStream; need to find how to read FileOutputStream
		try
		{
			//outputStream=openFileOutput(filename,Context.MODE_PRIVATE);
			outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
			outputStream.write(data.getBytes());
			
			outputStream.close();
			Log.d("DEBUG","SUCCESSFULLY WROTE TO FILE");
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
    //    File file=null;
		//Log.d("DEBUG","free space" +file.getFreeSpace());
	//	Log.d("DEBUG","total space"+file.getTotalSpace());
		return inflater.inflate(R.layout.frag_01,container,false);
		}
	
	public File getTempFile(Context context,String uri)//create a temporary file - if you want to cache a file
	{
		File file = null;
		Log.d("DEBUG","free space" +file.getFreeSpace());
		Log.d("DEBUG","total space"+file.getTotalSpace());
		try
		{
			String filename=Uri.parse(uri).getLastPathSegment();
			file=File.createTempFile(filename, null,context.getCacheDir());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return file;
	}
	public boolean isExternalStorageWritable()
	{
		String state=Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state))
			return true;
		return false;
	}
	public boolean isExternalStorageReadable()
	{
		String state=Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)||Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
			return true;
		return false;
	}
	
	public File getAlbumStorageDir(String albumName)//only for saving public files to external storage
	{
		File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),albumName);
		if(!file.mkdirs())
		{
			Log.e("LOG_TAG", "DIRECTORY NOT CREATED");
		}
		return file;
	}
    public File getAlbumStorageDir(Context context, String albumName)//only for saving private files to external storage
    {
    	File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),albumName);
    	if(!file.mkdirs())
    	{
    		Log.e("LOG_TAG","DIRECTORY NOT CREATED");
    	}
    	return file;
    }
}
