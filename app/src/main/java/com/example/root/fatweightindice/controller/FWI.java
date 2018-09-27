package com.example.root.fatweightindice.controller;

import android.content.Context;
import android.util.Log;

import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.business.FWIComputation;
import com.example.root.fatweightindice.view.ComputeActivity;

import java.util.List;

/**
 * It is very important to note that, the context could not be a field of this class;
 * because (as a servlet) it will be instanciated at once, field's context will imply
 * either same context/mainActivity for all user (what is absurd) or
 * context/mainActivity interferences between client request when there is a setting instruction for the context.
 * to increase the code beauty, we could try to store the context inside a "session" scope variable(as in servlet API).
 * @author TSIMBO FOKOU
 * @version 1.0
 *
 */
public final class FWI {

    private static FWI fwi = null;
    /**
     * This list should be initialize at the application launch from the database,
     * by its getter. And after it will be update by add() follow by a set().
     */
    private List<Profile> profiles = null;
    private Profile selectedProfile = null;

    /**
     * private constructor which make sure that an instance can not be create in every manner.
     */
    private FWI(){
        super();
    }

    /**
     * This method is used to have the unique instance of our controller.
     * @return return the already create FWI or the created one else
     */
    public static FWI getInstance(){

        Log.d("INFO", "****** getInstance *********" );
        if(fwi!=null){
            return fwi;
        } else {
            fwi = new FWI();
            return fwi;
        }
    }

    /**
     * Set the list of profiles by initialize them
     * @param profiles the list of profiles
     */
    public void setProfiles(List<Profile> profiles){
        this.profiles = profiles;
    }

    /**
     * Get the current list of profiles
     * @return the current list of profiles
     */
    public List<Profile> getProfiles(){
        return this.profiles;
    }

    /**
     * Get the selected profile from the History activity
     * @return the selected profile from the history activity
     */
    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    /**
     * Set the selected profile from the History activity.
     * @param selectedProfile
     */
    public void setSelectedProfile(Profile selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    /**
     * This is the method that is going to manage the work inside the controller FWI,
     * as a servlet in a Web App.
     * @param context : This is the context of the ComputeActivity
     * @param weight
     * @param size
     * @param age
     * @param sex
     * @return the profile that has been created
     */
    public Profile service(Context context, String weight, String size, String age, Boolean sex){

        //ComputeActivity main = (ComputeActivity)context;
        // we can use it to access to the layouts
        Log.d("INFO", "****** service "+context.toString()+"********");
        FWIComputation fwiComputation = new FWIComputation(context);
        Profile profile = fwiComputation.createProfile(convert(weight), convert(size), convert(age), sex==true ? 1 : 0);
        return profile;
    }

    /**
     * This surcharget holds to treat the recovery of the last profile.
     * @param context : This is the context of the ComputeActivity
     * @return
     */
    public Profile service(Context context){
        Log.d("INFO", "****** service' " + context.toString() +"********");
        FWIComputation fwiComputation = new FWIComputation(context);
        //return fwiComputation.deserializeProfile();
        return fwiComputation.getLastProfile();
    }

    /**
     *
     * @param context
     * @param profile
     */
    public void setProfile(Context context, Profile profile){
        Log.d("INFO", "****** setProfile : FWI : " +context.toString()+" *********");
        ((ComputeActivity)context).setProfile(profile);
    }

    /**
     * Initialize data from the database.
     */
    public void initData(){
        FWIComputation fwiComputation = new FWIComputation(null);
        fwiComputation.getListProfiles();
    }

    /**
     * Delete a profile from the database and the profile's collections
     * @param profile the profile to delete;
     */
    public void deleteProfile(Profile profile){
        FWIComputation fwiComputation = new FWIComputation(null);
        fwiComputation.deleteProfile(profile);
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

