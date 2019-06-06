package pansong291.dextool.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;

public class CmdTextView extends TextView
{
 boolean isUserScrolling;
 VerticalSeekBar mVSeekBar;
 
 public CmdTextView(android.content.Context context)
 {
  super(context);
  defSettings();
 }

 public CmdTextView(Context context, AttributeSet attrs)
 {
  super(context, attrs);
  defSettings();
 }

 public CmdTextView(Context context, AttributeSet attrs, int defStyleAttr)
 {
  super(context, attrs, defStyleAttr);
  defSettings();
 }

 public CmdTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
 {
  super(context, attrs, defStyleAttr, defStyleRes);
  defSettings();
 }
 
 public final void bindVerticalSeekBar(final VerticalSeekBar vsb)
 {
  mVSeekBar = vsb;
 }

 @Override
 protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert)
 {
  super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
  if(isUserScrolling && mVSeekBar != null)
  {
   int offset = getLineCount()
    * getLineHeight()
    - getHeight();
   int progress = vert * mVSeekBar.getMax() / offset;
   mVSeekBar.setProgress(mVSeekBar.getMax() - progress);
  }
 }

 @Override
 public boolean onTouchEvent(MotionEvent event)
 {
  isUserScrolling = true;
  return super.onTouchEvent(event);
 }
 
 public void scrollContentTo(int x, int y)
 {
  isUserScrolling = false;
  super.scrollTo(x, y);
 }
 
 private void defSettings()
 {
  setTextIsSelectable(true);
  applyCustomFont(getContext());
 }
 
 private void applyCustomFont(Context context)
 {
  setTypeface(FontCache.getTypeface(FontCache.DROID_SANS_MONO, context));
 }
 
}
