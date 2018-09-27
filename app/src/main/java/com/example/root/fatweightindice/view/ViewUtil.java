package com.example.root.fatweightindice.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class ViewUtil {

    /**
     * This method listener a performed click on an ImageButton and the make a map from one activity to another
     * @param imageButton to listener
     * @param context of the actual activity, ie, the old activity of the mapping
     * @param classe of the activity, ie, the new activity of the mapping
     */
    public static void listenerButtonMap(ImageButton imageButton, final Context context, final Class classe){
        imageButton.setOnClickListener(new ImageButton.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
    }

    /**
     * Format a float into the string pattern "+.?", ie, number which one digit after the point
     * @param value to format
     * @return the format string value
     */
    public static String decimalFormat(Float value){
        return String.format("%.01f", value);
    }
}
