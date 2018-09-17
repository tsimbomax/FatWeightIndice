package com.example.root.fatweightindice.controller;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.business.FWIComputation;

public final class FWI {

    private Context context;
    private static FWI fwi = null;

    /**
     * private constructor which make sure that an instance can not be create in every manner.
     * @param context : the context of the application ie. the MainActivity.
     */
    private FWI(Context context){
        this.context = context;
    }

    /**
     * This method is used to have the unique instance of our controller.
     * @param context : This is the context of the MainActivity
     * @return return the already create FWI or the created one else
     */
    public static FWI getInstance(Context context){

        Log.d("INFO", "****** getInstance *********");
        if(fwi!=null){
            return fwi;
        } else {
            fwi = new FWI(context);
            return fwi;
        }
    }

    /**
     * This is the method that is going to manage the work inside the controller FWI,
     * as a servlet in a Web App.
     * @param weight
     * @param size
     * @param age
     * @param sex
     * @return the profile that has been created
     */
    public Profile service(String weight, String size, String age, Boolean sex){

        Log.d("INFO", "****** service ********");
        FWIComputation fwiComputation = new FWIComputation(this.context);
        Profile profile = fwiComputation.createProfile(convert(weight), convert(size), convert(age), sex==true ? 1 : 0);
        return profile;
    }

    /**
     * This surcharget holds to treat the recovery of the last profile.
     * @return
     */
    public Profile service(){
        Log.d("INFO", "****** service' ********");
        FWIComputation fwiComputation = new FWIComputation(this.context);
        //return fwiComputation.deserializeProfile();
        return fwiComputation.getLastProfile();
    }

    /**
     * This method parse a String into an Integer and print a stack trace if an exception occurs
     * @param sValue : is the String to convert into an Integer
     * @return the int value associated or null else.
     */
    private Integer convert(String sValue){
        Log.d("INFO", "****** convert *********");
        Integer iValue = null;
        try{
            iValue = Integer.parseInt(sValue);
        } catch (NumberFormatException e){
            e.printStackTrace();
        } finally{
            return iValue;
        }
    }

}

