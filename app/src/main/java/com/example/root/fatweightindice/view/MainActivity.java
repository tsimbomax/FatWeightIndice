package com.example.root.fatweightindice.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.fatweightindice.R;
import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.controller.FWI;

public class MainActivity extends AppCompatActivity {

    private EditText txtWeight;
    private EditText txtSize;
    private EditText txtAge;
    private RadioButton rbMan;
    private Button btnCompute;
    private ImageView imgFWI;
    private TextView txtComment;

    private FWI fwi;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewManagement();
    }

    /**
     * Main method of the MainActivity.
     * It manages the different method of its class.
     */
    private void viewManagement(){

        Log.d("INFO", "The application has been deployed *************");

        initParam();
        Log.d("INFO", "The layouts have been mapped in the application**************");

        listenerNewProfile();
        }

    /**
     * This method initialize the layouts.
     */
    private void initParam(){

        txtWeight = (EditText) findViewById(R.id.txtWeight);
        txtSize = (EditText) findViewById(R.id.txtSize);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rbMan = (RadioButton) findViewById(R.id.rbMan);
        btnCompute = (Button) findViewById(R.id.btnCompute);
        imgFWI = (ImageView) findViewById(R.id.imgFWI);
        txtComment = (TextView) findViewById(R.id.txtComment);
    }

    /**
     * This method listeners the click on the F.W.I's computation button.
     * When the click happen, it create a controller an call the service method.
     */
    private void listenerNewProfile(){

        btnCompute.setOnClickListener(new Button.OnClickListener(){
           public void onClick(View v){
               Log.d("INFO", "A click happen on the f.w.i computation button*************");
               MainActivity.this.fwi = FWI.getInstance(MainActivity.this);
               MainActivity.this.profile = fwi.service(txtWeight.getText().toString(), txtSize.getText().toString(), txtAge.getText().toString(), rbMan.isChecked());
               display();
           }
        });
    }

    /**
     * This method display the result of the computation of the F.W.I and the comment.
     */
    private void display(){

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