package pansong291.dextool.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.io.PrintStream;
import pansong291.dextool.R;
import pansong291.dextool.other.CmdTask;
import pansong291.dextool.other.TextPrintStream;
import pansong291.dextool.view.CmdTextView;
import pansong291.dextool.view.VerticalSeekBar;

public class CmdActivity extends Zactivity
{
 Class<?> clazz;
 String[] args;
 CmdTextView txt_out;
 VerticalSeekBar vsb_out;
 Handler handler;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_cmd);
  
  clazz = (Class<?>)getIntent().getSerializableExtra("class");
  args = getIntent().getStringArrayExtra("args");
  
  txt_out = findViewById(R.id.txt_out);
  vsb_out = findViewById(R.id.vsb_out);
  txt_out.bindVerticalSeekBar(vsb_out);
  vsb_out.bindCmdTextView(txt_out);
  
  handler = new Handler()
  {
   public void handleMessage(Message msg)
   {
    if(msg.what == 1)
    {
     txt_out.append(msg.obj.toString());
     int offset = txt_out.getLineCount()
      * txt_out.getLineHeight()
      - txt_out.getHeight();
     if(offset > 0)
     {
      txt_out.scrollTo(0, offset);
      vsb_out.setVisibility(View.VISIBLE);
     }else
     {
      vsb_out.setVisibility(View.GONE);
     }
    }
   }
  };
  
  PrintStream ps = new PrintStream(new TextPrintStream(handler));
  System.setOut(ps);
  System.setErr(ps);
  
  new Thread(new CmdTask(clazz, args)).start();
 }
 
}
