package pansong291.dextool.view;

import android.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pansong291.dextool.view.FontCache;

public class SpinnerArrayAdapter extends ArrayAdapter<Class<?>>
{
 private LayoutInflater mInflater;

 public SpinnerArrayAdapter(Context context, Class<?>[] list)
 {
  super(context, R.layout.simple_spinner_item, list);
  mInflater = LayoutInflater.from(context);
  setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent)
 {
  return createViewFromResource(position, convertView, parent, R.layout.simple_spinner_item);
 }

 @Override
 public View getDropDownView(int position, View convertView, ViewGroup parent)
 {
  return createViewFromResource(position, convertView, parent, R.layout.simple_spinner_dropdown_item);
 }

 private View createViewFromResource(int position, View convertView, ViewGroup parent, int resource)
 {
  View view;
  TextView text;

  if(convertView == null)
  {
   view = mInflater.inflate(resource, parent, false);
  }else
  {
   view = convertView;
  }

  try
  {
   int mFieldId = R.id.text1;
   if(mFieldId == 0)
   {
    //  If no custom field is assigned, assume the whole resource is a TextView
    text = (TextView) view;
   }else
   {
    //  Otherwise, find the TextView field within the layout
    text = (TextView) view.findViewById(mFieldId);
   }
   text.setTypeface(FontCache.getTypeface(FontCache.DROID_SANS_MONO, getContext()));
  }catch(ClassCastException e)
  {
   Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
   throw new IllegalStateException(
    "ArrayAdapter requires the resource ID to be a TextView", e);
  }

  String item = getItem(position).getSimpleName();
  if(item instanceof CharSequence)
  {
   text.setText((CharSequence)item);
  }else
  {
   text.setText(item.toString());
  }

  return view;
 }

}
