package com.example.root.fatweightindice.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.fatweightindice.R;
import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.controller.FWI;

public class ComputeActivity extends AppCompatActivity {

    private EditText txtWeight;
    private EditText txtSize;
    private EditText txtAge;
    private RadioButton rbMan;
    private RadioButton rbWoman;
    private Button btnCompute;
    private ImageView imgFWI;
    private TextView txtComment;
    private ImageButton btnHome;

    private FWI fwi = null;
    private Profile profile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);
        viewManagement();
    }

    /**
     * Main method of the ComputeActivity.
     * It manages the different method of its class.
     */
    private void viewManagement(){

        Log.d("INFO", "The application has been deployed *************");

        initParam();
        Log.d("INFO", "The layouts have been mapped in the application**************");

        this.fwi = FWI.getInstance();
        //this.profile = fwi.service(this);
        //Log.d("INFO", "The recovery process held **************");

        this.profile = fwi.getSelectedProfile();
        //reset to null of the selectProfile
        fwi.setSelectedProfile(null);
        Log.d("INFO", "The transmission profile held **************");

        display();

        listenerNewProfile();
        ViewUtil.listenerButtonMap(btnHome, this, MainActivity.class);
    }

    /**
     * This method initialize the layouts.
     */
    private void initParam(){

        this.txtWeight = (EditText) findViewById(R.id.txtWeight);
        this.txtSize = (EditText) findViewById(R.id.txtSize);
        this.txtAge = (EditText) findViewById(R.id.txtAge);
        this.rbMan = (RadioButton) findViewById(R.id.rbMan);
        this.rbWoman = (RadioButton) findViewById(R.id.rbWoman);
        this.btnCompute = (Button) findViewById(R.id.btnCompute);
        this.imgFWI = (ImageView) findViewById(R.id.imgFWI);
        this.txtComment = (TextView) findViewById(R.id.txtComment);
        this.btnHome = findViewById(R.id.btnHome);
    }

    /**
     * This method listeners the click on the F.W.I's computation button.
     * When the click happen, it create a controller an call the service method.
     */
    private void listenerNewProfile(){

        btnCompute.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
               Log.d("INFO", "A click happen on the f.w.i computation button*************");
               ComputeActivity.this.fwi = FWI.getInstance();
               ComputeActivity.this.profile = fwi.service(ComputeActivity.this,txtWeight.getText().toString(), txtSize.getText().toString(), txtAge.getText().toString(), rbMan.isChecked());
               display();
           }
        });
    }

    /**
     * This method set profile from outside and forced its displaying
     * @param profile to be set and display
     */
    public void setProfile(Profile profile){
        this.profile = profile;
        display();
    }

    /**
     * This method display the result of the computation of the F.W.I and the comment.
     */
    private void display(){

        Log.d("INFO", "inside the display method **************");
        if(profile == null)
            return;

        txtWeight.setText(profile.getWeight().toString());
        txtSize.setText(profile.getSize().toString());
        txtAge.setText(profile.getAge().toString());
        if(profile.getSex() == 0)
            rbWoman.setChecked(true);

        String comment = profile.getComment();

        if(comment.equals("Normal")){
            imgFWI.setImageResource(R.drawable.index1);
        }else if(comment.equals("Too thin")){
            imgFWI.setImageResource(R.drawable.index2);
        } else if(comment.equals("Too fat")){
            imgFWI.setImageResource(R.drawable.index3);
        } else{
            Toast.makeText(this, comment + " please try again!", Toast.LENGTH_SHORT).show();
            return;
        }
        txtComment.setText(String.format("%.01f", profile.getFwi()) + " F.W.I " + comment);
        Log.d("INFO", "A new profile has been compute******************");
    }
}
