package com.example.root.fatweightindice.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.root.fatweightindice.R;
import com.example.root.fatweightindice.controller.FWI;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnMenuFWI;
    private ImageButton btnMenuHistory;
    private static int number = 0;

    /**
     * @see AppCompatActivity#onCreate(Bundle)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewManager();
    }

    /**
     * Manage all actions in the Activity
     */
    private void viewManager(){
        number++;
        Log.d("EXP", " ********* " + number + " ********");
        initParam();
        ViewUtil.listenerButtonMap(btnMenuFWI, this, ComputeActivity.class);
        ViewUtil.listenerButtonMap(btnMenuHistory, this, HistoryActivity.class);
        FWI fwi = FWI.getInstance();
        if(number == 1)
            fwi.initData();
    }

    /**
     * initialize the parameters.
     */
    private void initParam(){
        this.btnMenuFWI = (ImageButton)findViewById(R.id.btnMenuFWI);
        this.btnMenuHistory = (ImageButton)findViewById(R.id.btnMenuHistory);
    }
}
