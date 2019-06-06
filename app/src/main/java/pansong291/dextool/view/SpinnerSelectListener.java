package pansong291.dextool.view;

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.view.View;
import pansong291.dextool.activity.MainActivity;
import proguard.ProGuard;

public class SpinnerSelectListener implements OnItemSelectedListener
{
 View view;
 
 public SpinnerSelectListener(View v)
 {
  view = v;
 }

 @Override
 public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
 {
  if(MainActivity.CLASSES[p3] == ProGuard.class)
  {
   view.setVisibility(View.VISIBLE);
  }else
  {
   view.setVisibility(View.GONE);
  }
 }

 @Override
 public void onNothingSelected(AdapterView<?> p1)
 {
  // TODO: Implement this method
 }
 
}
