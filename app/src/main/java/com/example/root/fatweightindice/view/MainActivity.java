package com.example.root.fatweightindice.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.root.fatweightindice.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnMenuFWI;
    private ImageButton btnMenuHistory;

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
        initParam();
        ViewUtil.listenerButtonMap(btnMenuFWI, this, ComputeActivity.class);
        //ViewUtil.listenerButtonMap(btnMenuHistory, this, HistoryActivity.class);
    }

    /**
     * initialize the parameters.
     */
    private void initParam(){
        this.btnMenuFWI = (ImageButton)findViewById(R.id.btnMenuFWI);
        this.btnMenuHistory = (ImageButton)findViewById(R.id.btnMenuHistory);
    }
}
