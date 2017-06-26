package com.flyingtree.kiranpuppala.shouldwakeup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kiranpuppala on 18/06/17.
 */

public class SetAlarmActivity extends AppCompatActivity {
    private ImageView puzzleAdd;
    private TextView puzzleAddText;
    private View.OnClickListener onClickListener;
    private  PuzzleListAdapter adapter;
    private  ListView listView;
    private  ArrayList<String>  values;
    private String title  = "Set Alarm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm_layout);

        puzzleAdd = (ImageView) findViewById(R.id.puzzleAdd) ;
        puzzleAddText = (TextView) findViewById(R.id.puzzleAddText);

        puzzleAdd.setOnClickListener(myButtonListener);
        puzzleAddText.setOnClickListener(myButtonListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.neat)), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        getSupportActionBar().setTitle(s);
        getSupportActionBar().setSubtitle("");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.neat), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        values = new ArrayList<String>();
        values.add(0,"Remember");
        values.add(1,"Solve");
        adapter = new PuzzleListAdapter(this, values);
        listView = (ListView) findViewById(R.id.puzzleListView);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener myButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(SetAlarmActivity.this,ChoosePuzzleActivity.class);
            startActivity(intent);


//            int index = 0;
//            try {
//                if(adapter.getCount()>0) {
//                    index = adapter.getCount() - 1;
//                }
//                    adapter.values.add(index, "Solve");
//                    adapter.notifyDataSetChanged();
//
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }

        }
    };


}
