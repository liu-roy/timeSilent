package com.example.timesilent;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity implements OnClickListener 
{

	
	private SharedPreferences sharedPreferences1;
	private SharedPreferences sharedPreferences2;
	private TextView tvAlarmRecord1;
	private TextView tvAlarmRecord2;
	@Override
	public void onClick(View v) {
		View view =getLayoutInflater().inflate(R.layout.time,null);
		final TimePicker timePicker1=(TimePicker)view.findViewById(R.id.timepicker1);
		
		timePicker1.setIs24HourView(true);
		
		switch(v.getId())
		{
			 case R.id.btnAddAlarm1:
			 {
				 new AlertDialog.Builder(this).setTitle("设置静音开始时间").setView(view).setPositiveButton("确定",new DialogInterface.OnClickListener(){
					

					@Override
				public void onClick(DialogInterface dialog,int which)
					 {
						 String timeStr=String.valueOf(timePicker1.getCurrentHour())+":"+String.valueOf(timePicker1.getCurrentMinute());
						
						tvAlarmRecord1.setText(tvAlarmRecord1.getText().toString()+"\n"+timeStr);
						 sharedPreferences1.edit().putString(timeStr,timeStr).commit();
					 }
				 }).setNegativeButton("取消",null).show();
				 break;
			 }
			 case R.id.btnAddAlarm2:
			 {
				 new AlertDialog.Builder(this).setTitle("设置静音结束时间").setView(view).setPositiveButton("确定",new DialogInterface.OnClickListener(){
					 @Override
					 public void onClick(DialogInterface dialog,int which)
					 {
						 String timeStr=String.valueOf(timePicker1.getCurrentHour())+":"+String.valueOf(timePicker1.getCurrentMinute());
						
						tvAlarmRecord2.setText(tvAlarmRecord2.getText().toString()+"\n"+timeStr);
						 sharedPreferences2.edit().putString(timeStr,timeStr).commit();
					 }
				 }).setNegativeButton("取消",null).show();
				 break;
			 }
		}
	
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			Button btnAddAlarm1 = (Button) findViewById(R.id.btnAddAlarm1);
			Button btnAddAlarm2 = (Button) findViewById(R.id.btnAddAlarm2);
			tvAlarmRecord1 = (TextView) findViewById(R.id.tvAlarmRecord1);
			tvAlarmRecord2 = (TextView) findViewById(R.id.tvAlarmRecord2);
			btnAddAlarm1.setOnClickListener(this);
			btnAddAlarm2.setOnClickListener(this);
			
			sharedPreferences1 = getSharedPreferences("alarm_record1",
					Activity.MODE_PRIVATE);
			sharedPreferences2 = getSharedPreferences("alarm_record2",
					Activity.MODE_PRIVATE);

			AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			Intent intent = new Intent(this, TimeReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
					intent, 0);
			alarmManager.setRepeating(AlarmManager.RTC, 0, 60 * 1000, pendingIntent);

		}
	
	

}


