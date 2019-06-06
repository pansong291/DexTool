package pansong291.dextool.activity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import pansong291.dextool.R;


public class SettingsActivity extends PreferenceActivity implements OnPreferenceClickListener
{
 CheckBoxPreference cbp1;
 ListPreference lp1;
 EditTextPreference edtp1;
 Preference about;
 SharedPreferences sp;
 private AlertDialog altdlg;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  addPreferencesFromResource(R.xml.settings);
  
  cbp1=(CheckBoxPreference)findPreference("checkBox1");
  lp1=(ListPreference)findPreference("list1");
  edtp1=(EditTextPreference)findPreference("edt1");
  
  sp=getSharedPreferences(getPackageName()+"_preferences", 0);
  about=findPreference("aboutme");
  about.setSummary("关于说明");
  
  about.setOnPreferenceClickListener(this);
 }

 @Override
 protected void onResume()
 {
  super.onResume();
 }

 @Override
 public boolean onPreferenceClick(Preference p1)
 {
  switch(p1.getKey())
  {
   case "aboutme":
    if(altdlg == null)
     altdlg=new AlertDialog.Builder(this)
     .setTitle("关于")
     .setMessage("关于信息\n\n")
     .setNegativeButton("确定", null)
     .create();
    altdlg.show();
   break;
  }
  return true;
 }

 @Override
 public void onBackPressed()
 {
  // TODO: Implement this method
  super.onBackPressed();
 }


}