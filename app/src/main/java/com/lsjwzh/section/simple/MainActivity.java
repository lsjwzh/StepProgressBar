package com.lsjwzh.section.simple;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lsjwzh.widget.StepProgressBar;

public class MainActivity extends AppCompatActivity {
    private StepProgressBar mStepBar1;
    private StepProgressBar mStepBar2;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStepBar1 = (StepProgressBar) findViewById(R.id.step_bar1);
        mStepBar2 = (StepProgressBar) findViewById(R.id.step_bar2);
        mStepBar2.setMaxStep(10);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mStepBar2.setProgressStep(10);
            }
        }, 2000);
    }

}
