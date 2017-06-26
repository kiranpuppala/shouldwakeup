package com.flyingtree.kiranpuppala.shouldwakeup;

/**
 * Created by kiranpuppala on 26/06/17.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * Created by kiranpuppala on 26/06/17.
 */

public class EquationDialog {
    ImageView statusImage,closeImage;
    TextView statusTitle,equationText,equationSubmit,equationReload;
    EditText equationResult;
    View customView;
    Activity activity;
    Typeface lightRoboto,boldRoboto;
    LinearLayout add,dismiss,equationContainer,equationActions;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    List<String> equationList = new ArrayList<String>(Arrays.asList("(40*8/5+(80-2))=","(30+(40/3*12))=","(30*12)+(40-12)="));

    public EquationDialog(Activity activity){
        this.activity=activity;

    }

    public void showPopUp() {
        builder = new AlertDialog.Builder(this.activity);
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();

        customView = layoutInflater.inflate(R.layout.equation_layout, null);

        lightRoboto=Typeface.createFromAsset(activity.getAssets(),"Roboto-Light.ttf");
        boldRoboto=Typeface.createFromAsset(activity.getAssets(),"Roboto-Medium.ttf");

        statusTitle = (TextView)customView.findViewById(R.id.statusTitle);
        statusTitle.setTypeface(lightRoboto);

        equationText = (TextView) customView.findViewById(R.id.equationText);
        equationText.setTypeface(boldRoboto);
        setEquationText();

        equationResult = (EditText) customView.findViewById(R.id.equationResult);
        equationResult.setTypeface(boldRoboto);

        equationSubmit = (TextView) customView.findViewById(R.id.equationSubmit);
        equationSubmit.setTypeface(boldRoboto);
        equationSubmit.setOnClickListener(buttonClickListener);


        equationReload = (TextView) customView.findViewById(R.id.equationReload);
        equationReload.setTypeface(boldRoboto);
        equationReload.setOnClickListener(buttonClickListener);


        equationContainer = (LinearLayout) customView.findViewById(R.id.equationContainer);
        equationActions = (LinearLayout) customView.findViewById(R.id.equationActions);

        statusImage = (ImageView) customView.findViewById(R.id.statusImage);
        closeImage = (ImageView) customView.findViewById(R.id.closeImage);
        add = (LinearLayout) customView.findViewById(R.id.add);
        dismiss = (LinearLayout) customView.findViewById(R.id.dismiss);

        closeImage.setOnClickListener(buttonClickListener);
        add.setOnClickListener(buttonClickListener);
        dismiss.setOnClickListener(buttonClickListener);

        builder.setView(customView);
        dialog =  builder.create();
        dialog.show();
    }

    public void hidePopup(){
        dialog.dismiss();
    }

    public void setEquationText(){
       Collections.shuffle(equationList);
        equationText.setText(equationList.get(0));
    }

    public void refreshEquation(){
        setEquationText();
        statusImage.setVisibility(View.GONE);
        equationResult.setText("");
    }


    public void showSuccess(){
        statusImage.setImageResource(R.drawable.correct);
        statusImage.setVisibility(View.VISIBLE);
        equationActions.setVisibility(View.GONE);

    }
    public void showFailure(){
        statusImage.setImageResource(R.drawable.wrong);
        statusImage.setVisibility(View.VISIBLE);
    }

    public int getEquationResult(String equation){

        if(equation == "(40*8/5+(80-2))="){
            return (40*8/5+(80-2));
        }
        else if(equation == "(30+(40/3*12))="){
            return (30+(40/3*12));
        }
        else if(equation == "(30*12)+(40-12)="){
            return (30*12)+(40-12);
        }

       return 0;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.add) {

            }
            if (v.getId() == R.id.dismiss) {
                hidePopup();
            }
            if (v.getId() == R.id.closeImage) {
                hidePopup();
            }
            if(v.getId() == R.id.equationSubmit){
                try {
                    int result = parseInt(equationResult.getText().toString());
                    int output = getEquationResult(equationText.getText().toString());
                    if(result == output){
                        showSuccess();
                    }
                    else {
                        showFailure();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
            if(v.getId() == R.id.equationReload){
                refreshEquation();
            }

        }

    };



}
