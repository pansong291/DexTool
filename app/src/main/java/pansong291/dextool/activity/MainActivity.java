package pansong291.dextool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.googlecode.d2j.jasmin.Jar2JasminCmd;
import com.googlecode.d2j.jasmin.Jasmin2JarCmd;
import com.googlecode.d2j.smali.BaksmaliCmd;
import com.googlecode.d2j.smali.SmaliCmd;
import com.googlecode.dex2jar.tools.ApkSign;
import com.googlecode.dex2jar.tools.AsmVerify;
import com.googlecode.dex2jar.tools.ClassVersionSwitch;
import com.googlecode.dex2jar.tools.DecryptStringCmd;
import com.googlecode.dex2jar.tools.Dex2jarCmd;
import com.googlecode.dex2jar.tools.DexRecomputeChecksum;
import com.googlecode.dex2jar.tools.DexWeaverCmd;
import com.googlecode.dex2jar.tools.Jar2Dex;
import com.googlecode.dex2jar.tools.JarAccessCmd;
import com.googlecode.dex2jar.tools.JarWeaverCmd;
import com.googlecode.dex2jar.tools.StdApkCmd;
import pansong291.dextool.R;
import pansong291.dextool.view.FontCache;
import pansong291.dextool.view.SpinnerArrayAdapter;
import proguard.ProGuard;
import proguard.Configuration;
import proguard.obfuscate.SimpleNameFactory;
import android.widget.CheckBox;
import pansong291.dextool.view.SpinnerSelectListener;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;

public class MainActivity extends Zactivity 
{
 public static final Class<?>[] CLASSES =
 {
  ApkSign.class, AsmVerify.class, BaksmaliCmd.class,
  ClassVersionSwitch.class, DecryptStringCmd.class,
  Dex2jarCmd.class, DexRecomputeChecksum.class,
  DexWeaverCmd.class, Jar2Dex.class, Jar2JasminCmd.class,
  JarAccessCmd.class, JarWeaverCmd.class, Jasmin2JarCmd.class,
  ProGuard.class, SmaliCmd.class, StdApkCmd.class
  /** dex2jar-0.0.9.15
  ApkSign.class, AsmVerify.class, DecryptStringCmd.class,
  Dex2jarCmd.class, JarAccessCmd.class, Jar2Dex.class,
  ProGuard.class/**/
 };
 
 Spinner spinner_cmd;
 EditText edt_cmd;
 
 View ll_proguard_optins;
 CheckBox cb_use_obf_char, cb_random;
 EditText edt_obf_char;
 
 SpinnerArrayAdapter adapter_spinner;
 SpinnerSelectListener listener_spinner;
 
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  
  spinner_cmd = findViewById(R.id.spinner_cmd);
  edt_cmd = findViewById(R.id.edt_cmd);
  
  ll_proguard_optins = findViewById(R.id.ll_proguard_options);
  cb_use_obf_char = findViewById(R.id.cb_use_obf_char);
  cb_random = findViewById(R.id.cb_random);
  edt_obf_char = findViewById(R.id.edt_obf_char);
  
  edt_cmd.setTypeface(FontCache.getTypeface(FontCache.DROID_SANS_MONO, this));
  
  adapter_spinner = new SpinnerArrayAdapter(this, CLASSES);
  spinner_cmd.setAdapter(adapter_spinner);
  listener_spinner = new SpinnerSelectListener(ll_proguard_optins);
  spinner_cmd.setOnItemSelectedListener(listener_spinner);
 }
 
 public void onExecuteClick(View v)
 {
  if(CLASSES[spinner_cmd.getSelectedItemPosition()] == ProGuard.class)
  {
   if(cb_use_obf_char.isChecked())
   {
    Configuration.characterNames = edt_obf_char.getText().toString();
   }else
   {
    Configuration.characterNames = null;
   }
   Configuration.characterNameRandom = cb_random.isChecked();
  }
  
  Intent it = new Intent(this, CmdActivity.class);
  it.putExtra("class", CLASSES[spinner_cmd.getSelectedItemPosition()]);
  it.putExtra("args", getCmdParameters());
  startActivity(it);
 }
 
 private String[] getCmdParameters()
 {
  String s = edt_cmd.getText().toString();
  if(s.length() <= 0)
   return new String[]{};
  
  StringBuilder sb = new StringBuilder();
  
  char ch, lastChar = ' ';
  
  for(int i = 0;i < s.length(); i++)
  {
   ch = s.charAt(i);
   if(Character.isWhitespace(ch))
   {
    if(sb.length() > 0)
    {
     if(!Character.isWhitespace(lastChar))
     {
      sb.append(' ');
     }
    }
   }else
   {
    sb.append(ch);
   }
   lastChar = ch;
  }
  if(sb.charAt(sb.length() - 1) == ' ')
   sb.deleteCharAt(sb.length() - 1);
  
  return sb.toString().split(" ");
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu)
 {
  getMenuInflater().inflate(R.menu.main_menu, menu);
  return super.onCreateOptionsMenu(menu);
 }
 
 AlertDialog dlg_instruction;

 @Override
 public boolean onOptionsItemSelected(MenuItem item)
 {
  switch(item.getItemId())
  {
   case R.id.menu_instruction:
    if(dlg_instruction == null)
     dlg_instruction = new AlertDialog.Builder(this)
      .setTitle("说明")
      .setIcon(android.R.drawable.ic_menu_help)
      .setMessage("目前已知 Dex2jarCmd 存在bug，该功能无法使用。\n\n运行 ProGuard 时将有额外的选项，各选项说明如下：\n\n>> 指定混淆字符串\n若选中，则使用给定字符来生成混淆名称，若给定字符为空则使用预置字符，-dontusemixedcaseclassnames 选项将不起作用；\n若不选中，则使用默认字符来生成混淆名称，默认字符为a到z与A到Z。\n\n>> 随机顺序\n该选项仅针对给定的混淆字符。\n若选中，则生成混淆名称时采用随机顺序；\n若不选中，则生成混淆名称时采用给定字符的顺序。")
      .setPositiveButton("确定", null)
      .create();
    dlg_instruction.show();
    break;
  }
  return super.onOptionsItemSelected(item);
 }
 
 
 
}
