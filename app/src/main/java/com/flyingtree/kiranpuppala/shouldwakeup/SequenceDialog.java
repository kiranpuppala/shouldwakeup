package com.flyingtree.kiranpuppala.shouldwakeup;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kiranpuppala on 26/06/17.
 */

public class SequenceDialog {
    ImageView sequencePreviewImage,topLeftSequence,topRightSequence,bottomLeftSequence,bottomRightSequence;
    ImageView statusImage,closeImage;
    TextView statusTitle;
    TableLayout sequenceTable;
    View customView;
    int sequenceCount=0;
    Activity activity;
    Typeface lightRoboto;
    CountDownTimer timer;
    LinearLayout add,dismiss;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    List<Integer> shapeSequence = new ArrayList<Integer>(Arrays.asList(R.drawable.circle, R.drawable.square,R.drawable.triangle,R.drawable.pentagon));

    public SequenceDialog(Activity activity){
        this.activity=activity;

    }

    public void showPopUp() {
        builder = new AlertDialog.Builder(this.activity);
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();

        customView = layoutInflater.inflate(R.layout.sequence_layout, null);
        sequencePreviewImage= (ImageView) customView.findViewById(R.id.sequencePreview);
        topLeftSequence = (ImageView) customView.findViewById(R.id.topLeftSequence);
        topRightSequence=(ImageView) customView.findViewById(R.id.topRightSequence);
        bottomLeftSequence = (ImageView) customView.findViewById(R.id.bottomLeftSequence);
        bottomRightSequence = (ImageView) customView.findViewById(R.id.bottomRightSequence);

        lightRoboto=Typeface.createFromAsset(activity.getAssets(),"Roboto-Light.ttf");
        statusTitle = (TextView)customView.findViewById(R.id.statusTitle);
        statusTitle.setTypeface(lightRoboto);


        topLeftSequence.setTag(R.drawable.circle);
        topRightSequence.setTag(R.drawable.square);
        bottomLeftSequence.setTag(R.drawable.pentagon);
        bottomRightSequence.setTag(R.drawable.triangle);

        topLeftSequence.setImageResource(R.drawable.circle);
        topRightSequence.setImageResource(R.drawable.square);
        bottomLeftSequence.setImageResource(R.drawable.pentagon);
        bottomRightSequence.setImageResource(R.drawable.triangle);

        topLeftSequence.setOnClickListener(onClickListener);
        topRightSequence.setOnClickListener(onClickListener);
        bottomLeftSequence.setOnClickListener(onClickListener);
        bottomRightSequence.setOnClickListener(onClickListener);


        sequenceTable = (TableLayout) customView.findViewById(R.id.sequenceTable);
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

        startSequencePreview();

    }

    public void hidePopup(){
        dialog.dismiss();
    }


    public void startSequencePreview(){
        Collections.shuffle(shapeSequence);
           timer =  new CountDownTimer(10000, 2000) {
            int count =0;

            @Override
            public void onTick(long millisUntilFinished) {
                setSequenceImage(shapeSequence.get(count));
                count++;
            }

            @Override
            public void onFinish() {
                try{
                    sequenceTable.setVisibility(View.VISIBLE);
                    sequencePreviewImage.setVisibility(View.GONE);
                    statusTitle.setText("Tap Sequence");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();

    }

    public void setSequenceImage(int id){
        sequencePreviewImage.setImageResource(id);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageView iv = (ImageView) customView.findViewById(v.getId());
            int imageTag = (Integer)iv.getTag();

            if(imageTag==shapeSequence.get(sequenceCount)){
                iv.setImageResource(R.drawable.correct);
            }
            else{
                showFailureMessage();
            }

            if(sequenceCount == 3){
                showSuccessMessage();
                sequenceCount=0;
            }

            if(sequenceCount<3){
                sequenceCount++;
            }


        }



    };

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v instanceof  LinearLayout){
                if(v.getId() == R.id.add){

                }
                if(v.getId() == R.id.dismiss){
                    hidePopup();
                }
            }
            if(v instanceof  ImageView){
                if(v.getId() == R.id.closeImage){
                    hidePopup();
                }
            }
        }
    };

    public void showSuccessMessage(){
        statusImage.setVisibility(View.VISIBLE);
        statusImage.setImageResource(R.drawable.correct);
        sequenceTable.setVisibility(View.GONE);
        statusTitle.setText("Well Done");
    }

    public void showFailureMessage(){
        statusImage.setVisibility(View.VISIBLE);
        statusImage.setImageResource(R.drawable.wrong);
        sequenceTable.setVisibility(View.GONE);
        statusTitle.setText("Failed");
    }

}
