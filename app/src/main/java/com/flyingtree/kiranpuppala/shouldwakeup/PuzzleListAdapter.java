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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class PuzzleListAdapter extends ArrayAdapter<String> {
    private final Context context;
    public final ArrayList<String> values;


    public PuzzleListAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.recent_layout_item, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.puzzle_option_item, parent, false);
        ImageView  puzzleIcon = (ImageView) rowView.findViewById(R.id.puzzleOptionIcon);
        ImageView puzzleRemove = (ImageView) rowView.findViewById(R.id.puzzleRemove);
        TextView puzzleText = (TextView) rowView.findViewById(R.id.puzzleOptionText);

        puzzleText.setText(values.get(position));

        if(values.get(position) == "Remember")
            puzzleIcon.setImageResource(R.drawable.ic_change_history_black_36dp);
        else if(values.get(position) == "Solve")
            puzzleIcon.setImageResource(R.drawable.ic_action_equation);

        puzzleRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             removeRow(position);
            }
        });

        return rowView;
    }

    public void removeRow(int index){
        values.remove(index);
        notifyDataSetChanged();
    }



}