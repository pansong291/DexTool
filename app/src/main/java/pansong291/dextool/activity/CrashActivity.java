package pansong291.dextool.activity;

import android.os.Bundle;
import android.widget.EditText;
import pansong291.dextool.R;
import pansong291.crash.CrashApplication;

public class CrashActivity extends Zactivity
{
 EditText merrorlogtxt;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_crash);
  merrorlogtxt = (EditText)findViewById(R.id.activityerrorTextView1);
  merrorlogtxt.setText(getIntent().getStringExtra(CrashApplication.ERROR_MESSAGE_TAG));
 }

 @Override
 public void onBackPressed()
 {
  super.onBackPressed();
  System.exit(0);
 }
 
 
 
}
