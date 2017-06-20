package com.example.timesilent;

import java.util.Calendar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.content.SharedPreferences;



public class TimeReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		
		SharedPreferences sharedPreferences1 = context.getSharedPreferences(
				"alarm_record1", Activity.MODE_PRIVATE);
		SharedPreferences sharedPreferences2 = context.getSharedPreferences(
				"alarm_record2", Activity.MODE_PRIVATE);
		String hour = String.valueOf(Calendar.getInstance().get(
				Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(Calendar.getInstance().get(
				Calendar.MINUTE));
		String time1 = sharedPreferences1.getString(hour + ":" + minute, null);
		String time2 = sharedPreferences2.getString(hour + ":" + minute, null);
		AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		if (time1!= null)
		{	
			
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		if (time2!= null)
		{	
			
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		
	}

	
	
}
