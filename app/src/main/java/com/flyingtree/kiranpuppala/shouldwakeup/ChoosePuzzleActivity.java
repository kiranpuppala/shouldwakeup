package com.flyingtree.kiranpuppala.shouldwakeup;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Random;

/**
 * Created by kiranpuppala on 18/06/17.
 */

public class ChoosePuzzleActivity extends AppCompatActivity {
    String actionTitle = "Choose a puzzle";
    private String[] values = new String[]{"Sequence","Equation","Dots","Shake","Word"};
    private ChoosePuzzleAdapter adapter;
    private ListView chooseListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.choose_puzzle_layout);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.neat), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        SpannableString s = new SpannableString(actionTitle);
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.neat)), 0, actionTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        getSupportActionBar().setTitle(s);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("");
        getSupportActionBar().setElevation(0);


        chooseListview = (ListView) findViewById(R.id.chooseListview);
        adapter = new ChoosePuzzleAdapter(this,values);
        chooseListview.setAdapter(adapter);
        chooseListview.setOnItemClickListener(itemClickListener);


    }

    private ListView.OnItemClickListener itemClickListener  = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
            if(position == 0) {
                SequenceDialog sequenceDialog = new SequenceDialog(ChoosePuzzleActivity.this);
                sequenceDialog.showPopUp();
            }
            else if (position == 1){
                EquationDialog equationDialog = new EquationDialog(ChoosePuzzleActivity.this);
                equationDialog.showPopUp();
            }
            else if (position == 3){
                ShakeDialog shakeDialog = new ShakeDialog(ChoosePuzzleActivity.this);
                shakeDialog.showPopUp();
            }

        }

    };


}