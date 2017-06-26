package com.flyingtree.kiranpuppala.shouldwakeup;

/**
 * Created by kiranpuppala on 18/06/17.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private Typeface weekTypeFace;
    private Typeface timeTypeFace;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.recent_layout_item, values);
        this.context = context;
        this.values = values;
        weekTypeFace=Typeface.createFromAsset(context.getAssets(),"Roboto-Light.ttf");
        timeTypeFace=Typeface.createFromAsset(context.getAssets(),"Roboto-Thin.ttf");

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.recent_layout_item, parent, false);
        TextView timeText = (TextView) rowView.findViewById(R.id.timeText);
        TextView weekText = (TextView) rowView.findViewById(R.id.weekText);
        Switch simpleSwitch = (Switch) rowView.findViewById(R.id.simpleSwitch);
        timeText.setText(values[position]);
        weekText.setText(values[position]);

        timeText.setTypeface(timeTypeFace,Typeface.BOLD);
        weekText.setTypeface(weekTypeFace,Typeface.NORMAL);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Checked state ",isChecked+""+"position"+position);
            }
        });
        return rowView;
    }


}


//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent)
//    {
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row = convertView;
//        YourWrapper wrapper = null;
//
//        if (row == null)
//        {
//            row = inflater.inflate(R.layout.recent_layout_item, parent, false);
//            wrapper = new YourWrapper (row);
//            row.setTag(wrapper);
//        }
//        else
//            wrapper = (YourWrapper) row.getTag();
//
//        wrapper.getButton().setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                // What you want
//            }
//        });
//
//        return row;
//    }
//
//public class YourWrapper
//{
//    private View base;
//    private Button button;
//
//    public YourWrapper(View base)
//    {
//        this.base = base;
//    }
//
//    public Button getButton()
//    {
//        if (button == null)
//        {
//            button = (Button) base.findViewById(R.id.your_button);
//        }
//        return (button`);
//    }
//}
