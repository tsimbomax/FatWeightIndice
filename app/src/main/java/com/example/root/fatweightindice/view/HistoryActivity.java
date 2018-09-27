package com.example.root.fatweightindice.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.root.fatweightindice.R;
import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.controller.FWI;

import java.util.Collections;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private FWI fwi;
    private ListView listHistory = null;
    private ImageButton btnHomeHist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        viewManager();
    }

    private void viewManager(){
        initParam();
        ViewUtil.listenerButtonMap(btnHomeHist, this, MainActivity.class);
        this.fwi = FWI.getInstance();
        createList();
    }

    private void initParam(){
        this.listHistory = findViewById(R.id.listHistory);
        this.btnHomeHist = findViewById(R.id.btnHomeHist);
    }

    private void createList(){
        List<Profile> profiles = fwi.getProfiles();
        if(profiles != null){
            // to sort a profiles collection, the profiles objects have to be comparable(implement Comparable).
            Collections.sort(profiles, Collections.<Profile>reverseOrder());
            ListView listHistory = findViewById(R.id.listHistory);
            ListAdapterHistory listAdapterHistory = new ListAdapterHistory(this, profiles);
            listHistory.setAdapter(listAdapterHistory);
        }
    }

    /**
     * save the selected profile and move to the computation activity.
     * @param profile the selected profile in the HistoryActivity
     */
    public void displayProfile(Profile profile){
        fwi.setSelectedProfile(profile);
        Intent intent = new Intent(HistoryActivity.this, ComputeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
