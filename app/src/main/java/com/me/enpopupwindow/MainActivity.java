package com.me.enpopupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.me.enpopupwindow.en.MyPopup;

public class MainActivity extends AppCompatActivity {

    private Button mBt;
    private RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        final MyPopup myPopup = new MyPopup(this);
        mBt = (Button) findViewById(R.id.bt);
        activityMain = (RelativeLayout) findViewById(R.id.activity_main);

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPopup.showAtLocation(activityMain, Gravity.CENTER,0,0);
            }
        });


    }
}
