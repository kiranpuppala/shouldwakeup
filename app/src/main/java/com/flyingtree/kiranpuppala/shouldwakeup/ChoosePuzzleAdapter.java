package com.flyingtree.kiranpuppala.shouldwakeup;

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

import java.util.ArrayList;

/**
 * Created by kiranpuppala on 25/06/17.
 */

public class ChoosePuzzleAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private Typeface weekTypeFace;
    private Typeface thinTypeFace;


    public ChoosePuzzleAdapter(Context context, String[] values) {
        super(context, R.layout.choose_puzzle_option, values);
        this.context = context;
        this.values = values;
        thinTypeFace=Typeface.createFromAsset(context.getAssets(),"Roboto-Thin.ttf");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.choose_puzzle_option, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.chooseOptionImage);
        setImage(imageView,position);
        TextView textView = (TextView) rowView.findViewById(R.id.chooseOptionText);
        textView.setText(values[position]);
        textView.setTypeface(thinTypeFace,Typeface.BOLD);
        return rowView;
    }


    private  void setImage(ImageView imageView,int position){
        if(values[position]=="Sequence"){
            imageView.setImageResource(R.drawable.ic_sequence);
        }
        else if (values[position] == "Equation"){
            imageView.setImageResource(R.drawable.ic_equation);
        }
        else if (values[position]=="Dots"){
            imageView.setImageResource(R.drawable.ic_dots);
        }
        else if(values[position]=="Shake"){
            imageView.setImageResource(R.drawable.ic_shake);
        }
        else if(values[position]=="Word"){
            imageView.setImageResource(R.drawable.ic_word);
        }
    }
}
