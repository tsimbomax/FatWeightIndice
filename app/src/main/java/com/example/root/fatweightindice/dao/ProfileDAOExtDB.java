package com.example.root.fatweightindice.dao;

import android.content.Context;
import android.util.Log;

import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.business.DateUtil;
import com.example.root.fatweightindice.business.FWIComputation;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileDAOExtDB implements AsyncResponse, ProfileDAO {

    private static final String FORMAT_PATTERN = "EEE MMM hh:mm:ss 'AWT' yyyy";
    private static final String SERVER_ADR ="http://192.168.1.100:8080/WebAppServer/fwi/profile";
    private Context context = null;

    public ProfileDAOExtDB(Context context){
        this.context = context;
    }

    /**
     * execution at the return from the external server
     * @param output
     */
    @Override
    public void processFinish(String output) throws DAOException{
        Log.d("INFO", "**************** processFinish *****************");
        Log.d("SERVER", "*************** " + output);

        String message[] = output.split(" % ");

        if(message.length>1){

            switch(message[0]){
                case "saveProfile" :
                    Log.d("SAVE", "*************** " + message[1]);
                    break;
                case "lastProfile" :
                    Log.d("LAST", "*************** " + message[1] + message[2]);
                    try {
                        JSONArray jsonArray = new JSONArray(message[2]);
                        Profile profile = mapJSONArrayToProfile(jsonArray);
                        FWIComputation.setProfile(context, profile);
                    } catch (JSONException e) {
                        throw new DAOException("Fail to open the JSON's data; " + e.toString());
                    }
                    break;
                case "listProfiles" :
                    Log.d("LIST", "*************** " + message );
                    try {
                        JSONArray jsonArray ;
                        List<Profile> profiles = new ArrayList<Profile>();
                        int max = message.length;
                        for(int i=2; i<max; i++){
                            jsonArray = new JSONArray(message[i]);
                            profiles.add(mapJSONArrayToProfile(jsonArray));
                        }
                        FWIComputation.setProfiles(profiles);
                    } catch (JSONException e) {
                        throw new DAOException("Fail to open the JSON's data; " + e.toString());
                    }
                    break;
                case "deleteProfile" :
                    Log.d("DEL", "*************** " + message[1] );
                    break;
                default:
                    Log.d("ERROR", "*************** " + message[1]);
            }
        }
        Log.d("INFO-BACK", "**************** processFinish *****************");
    }

    /**
     *
     * @param profile that is going to be store in the database
     * @throws DAOException
     */
    @Override
    public void saveProfile(Profile profile) throws DAOException {
        Log.d("INFO", "**************** saveProfile *****************");
        HttpAccess httpAccess = new HttpAccess();
        //Delagation link
        httpAccess.setDelegate(this);

        httpAccess.addParam("operation", "saveProfile");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(DateUtil.convertDateToString(profile.getDate()));
        jsonArray.put(profile.getWeight());
        jsonArray.put(profile.getSize());
        jsonArray.put(profile.getAge());
        jsonArray.put(profile.getSex());
        jsonArray.put(profile.getFwi());
        jsonArray.put(profile.getComment());

        httpAccess.addParam("data", jsonArray.toString());

        httpAccess.execute(SERVER_ADR);

    }

    /**
     * Get the last profile from the external database
     * @return the last profile store in the external database
     * @throws DAOException the error occur during the transaction
     */
    @Override
    public Profile getProfile() throws DAOException {

        Log.d("INFO", "**************** lastProfile *****************");

        HttpAccess httpAccess = new HttpAccess();
        //Delagation link
        httpAccess.setDelegate(this);

        httpAccess.addParam("operation", "lastProfile");

        httpAccess.execute(SERVER_ADR);

        /* we want the top process thread finished before we execute the bottom instructions*/
        /* because went the execute() is launched, a new thread is created, working in the background and then the last one continue */

        Log.d("INFO-BACK", "**************** lastProfile *****************");
        //Log.d("INFO-BACK", profile.getComment() +" *****************");
        //return this.profile;
        return null;
    }

    /**
     * Get the list of store profiles in the external database.
     * @return the list of profiles store in the external database.
     * @throws DAOException when error occur during the transaction.
     */
    @Override
    public List<Profile> getProfiles() throws DAOException {
        Log.d("INFO", "**************** listProfiles *****************");

        HttpAccess httpAccess = new HttpAccess();
        //Delagation link
        httpAccess.setDelegate(this);

        httpAccess.addParam("operation", "listProfiles");

        httpAccess.execute(SERVER_ADR);
/* we want the top process thread finished before we execute the return instruction*/
        //return this.profiles;
        return null;
    }

    /**
     * @see ProfileDAO#deleteProfile(String)
     * @param date the attribute date of the profile to delete.
     */
    @Override
    public void deleteProfile(String date){
        Log.d("INFO", "**************** listProfiles *****************");
        HttpAccess httpAccess = new HttpAccess();
        httpAccess.setDelegate(this);

        httpAccess.addParam("operation", "deleteProfile");
        httpAccess.addParam("date", date);

        httpAccess.execute(SERVER_ADR);
    }

    /**
     * A Map from a JSONArray to a profile
     * @param jsonArray the JSONArray that should be convert into a profile
     * @return the associated Profile to the input JSONArray
     * @throws DAOException when a data does not exist in the jsonArray or can not be convert.
     */
    private Profile mapJSONArrayToProfile(JSONArray jsonArray) throws DAOException{
        Log.d("INFO", "**************** mapJSONArrayToProfile *****************");
        Profile profile = null;
        Date date ;
        Integer weight = null ;
        Integer size = null ;
        Integer age = null;
        Integer sex = null ;
        Float fwi = null ;
        String comment = null ;

        //date = (Date) jsonArray.get( 0 );
        try {
            Log.d("INFO - DATE1", "**************** " + jsonArray.getString(0) +" *****************");
            date = DateUtil.convertStringToDate(jsonArray.getString(0));
            Log.d("INFO - DATE2", "**************** " + date +" *****************");
            weight = jsonArray.getInt( 1 );
            size =  jsonArray.getInt( 2 );
            age =  jsonArray.getInt( 3 );
            sex = jsonArray.getInt( 4 );
            fwi = (float)jsonArray.getDouble( 5 );
            comment = jsonArray.getString( 6 );
        } catch ( ParseException | JSONException e ) {
            throw new DAOException( "incorrect json data in the JSONArray ", e);
        }
        profile = new Profile(date, weight, size, age, sex);
        profile.setFwi(  fwi ) ;
        profile.setComment( comment );
        return profile;
    }
}
