package com.flyingtree.kiranpuppala.shouldwakeup;

/**
 * Created by kiranpuppala on 26/06/17.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShakeDialog implements SensorListener{
    ImageView statusImage,closeImage;
    ProgressBar shakeProgress;
    TextView statusTitle;
    View customView;
    Activity activity;
    Typeface lightRoboto,boldRoboto;
    LinearLayout add,dismiss;
    AlertDialog.Builder builder;
    AlertDialog dialog;


    private static final int FORCE_THRESHOLD = 350;
    private static final int TIME_THRESHOLD = 100;
    private static final int SHAKE_TIMEOUT = 500;
    private static final int SHAKE_DURATION = 1000;
    private static final int SHAKE_COUNT = 3;

    private SensorManager mSensorMgr;
    private float mLastX=-1.0f, mLastY=-1.0f, mLastZ=-1.0f;
    private long mLastTime;
    private Context mContext;
    private int mShakeCount = 0;
    private long mLastShake;
    private long mLastForce;

    public ShakeDialog(Activity activity){
        this.activity=activity;
        mContext=this.activity.getBaseContext();
        resume();

    }

    public void showPopUp() {
        builder = new AlertDialog.Builder(this.activity);
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();

        customView = layoutInflater.inflate(R.layout.shake_layout, null);

        lightRoboto=Typeface.createFromAsset(activity.getAssets(),"Roboto-Light.ttf");
        boldRoboto=Typeface.createFromAsset(activity.getAssets(),"Roboto-Medium.ttf");

        statusTitle = (TextView) customView.findViewById(R.id.statusTitle);
        statusTitle.setTypeface(lightRoboto);

        shakeProgress = (ProgressBar) customView.findViewById(R.id.shakeProgress);

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
        }
    };




    public void resume() {
        mSensorMgr = (SensorManager)mContext.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorMgr == null) {
            throw new UnsupportedOperationException("Sensors not supported");
        }
        boolean supported = mSensorMgr.registerListener(this, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
        if (!supported) {
            mSensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
            throw new UnsupportedOperationException("Accelerometer not supported");
        }
    }

    public void deregisterListener() {
        if (mSensorMgr != null) {
            mSensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
            mSensorMgr = null;
        }
    }

    public void onAccuracyChanged(int sensor, int accuracy) { }

    public void onSensorChanged(int sensor, float[] values){

        if (sensor != SensorManager.SENSOR_ACCELEROMETER) return;
        long now = System.currentTimeMillis();

        if ((now - mLastForce) > SHAKE_TIMEOUT) {
            mShakeCount = 0;
        }

        if ((now - mLastTime) > TIME_THRESHOLD) {
            long diff = now - mLastTime;
            float speed = Math.abs(values[SensorManager.DATA_X] + values[SensorManager.DATA_Y] + values[SensorManager.DATA_Z] - mLastX - mLastY - mLastZ) / diff * 10000;
            if (speed > FORCE_THRESHOLD) {
                if ((++mShakeCount >= SHAKE_COUNT) && (now - mLastShake > SHAKE_DURATION)) {
                    mLastShake = now;
                    mShakeCount = 0;

                }
                mLastForce = now;
            }
            mLastTime = now;
            mLastX = values[SensorManager.DATA_X];
            mLastY = values[SensorManager.DATA_Y];
            mLastZ = values[SensorManager.DATA_Z];

            if(mShakeCount<=6) {
                shakeProgress.setProgress(mShakeCount * 10);
            }
            else{
                freeSensors();
            }
        }

    }

    public void freeSensors(){
        shakeProgress.setProgress(100);
        deregisterListener();
        statusImage.setImageResource(R.drawable.correct);
        statusImage.setVisibility(View.VISIBLE);
    }



}
