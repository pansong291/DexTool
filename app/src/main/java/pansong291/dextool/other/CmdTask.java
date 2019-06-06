package pansong291.dextool.other;

import java.lang.reflect.Method;

public class CmdTask implements Runnable
{
 Class<?> clazz;
 String[] args;
 
 public CmdTask(Class<?> c, String[] s)
 {
  clazz = c;
  args = s;
 }

 @Override
 public void run()
 {
  try
  {
   Thread.sleep(200);
   System.out.println("The program is running, please don't exit this activity.\n");
   Method main = clazz.getMethod("main", String[].class);
   main.invoke(null, (Object)args);
   System.out.println("\nThe program finished, you can press back button now.");
  }catch(Exception e)
  {
   e.printStackTrace();
  }
 }
 
}
