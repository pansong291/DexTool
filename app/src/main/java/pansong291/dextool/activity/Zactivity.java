package pansong291.dextool.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import pansong291.crash.ASControl;

public class Zactivity extends Activity
{
 
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  ASControl.getASControl().addActivity(this);
 }

 @Override
 protected void onDestroy()
 {
  super.onDestroy();
  ASControl.getASControl().removeActivity(this);
 }
 
 public void toast(String s)
 {
  toast(s, 0);
 }
 
 public void toast(String s, int i)
 {
  Toast.makeText(this, s, i).show();
 }
 
}
