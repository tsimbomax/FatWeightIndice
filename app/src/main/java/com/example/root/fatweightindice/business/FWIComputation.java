package com.example.root.fatweightindice.business;

import android.content.Context;
import android.util.Log;

import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.dao.DAOException;
import com.example.root.fatweightindice.dao.DAOFactory;
import com.example.root.fatweightindice.dao.ProfileDAO;
import com.example.root.fatweightindice.dao.Serializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FWIComputation {

    private static final int MIN_WOMAN = 15;
    private static final int MAX_WOMAN = 30;
    private static final int MIN_MAN = 10;
    private static final int MAX_MAN = 25;

    private String fileName = "fileFWI";
    private Context context;
    private DAOFactory daoFactory;

    /**
     * Unique and public constructor
     */
    public FWIComputation(Context context){

        this.context = context;
        daoFactory = DAOFactory.getInstance();
    }

    //we can add a mutator for the field fileName,
    //In order to generated dynamically many  serialization from the controller FWI.

    /**
     * This method create a new profile.
     * @return the profile instance that have been created.
     */
    public Profile createProfile(Integer weight, Integer size, Integer age, Integer sex){

        Log.d("INFO", "****** createProfile ********");
        Profile profile = new Profile(new Date(), weight, size, age, sex);
        computeFWI(profile);
        interprateFWI(profile);

        /**
         * Normally, we should serialize only the last profile and not every profile.
         * To do that, we need to know how to treat the close software event.
         * In this case we should comment the following call instruction method,
         * and call the method directly from the controller FWI.
         */
        //serializeProfile(profile);
        ProfileDAO dao = daoFactory.getProfileDAO(context);
        try {
            dao.saveProfile(profile);
        } catch (DAOException e){
            e.printStackTrace();
        }

        return profile;
    }

    /**
     * get the last profile store in the database
     * @return the last profile that has been store
     */
    public Profile getLastProfile(){
        Log.d("INFO", "****** getLastProfile ********");
        ProfileDAO dao = daoFactory.getProfileDAO(this.context);
        Profile profile = null;
        try{
            profile = dao.getProfile();
            //This two steps do not have to be done in the deserialize process
            computeFWI(profile);
            interprateFWI(profile);
        } catch (DAOException e){
            e.printStackTrace();
        } finally {
            return profile;
        }
    }

    /**
     *Ask all instances of Profile in the database to the DAO
     * @return the list of profiles
     */
    public List<Profile> getListProfiles(){
        Log.d("INFO", "****** getListProfiles ********");
        List<Profile> profiles = new ArrayList<Profile>();
        for (Profile profile : profiles) {
            computeFWI(profile);
            interprateFWI(profile);
        }
        return profiles;
    }

    /**
     * This method compute the F.W.I. of the field object profile and store the answer.
     * @param profile
     */
    public void computeFWI(Profile profile){

        Log.d("INFO", "****** comuteFWI ********");
        float size = (float) profile.getSize()/100;
        Float fwi;
        if(size!=0) {
            fwi = (float) ((1.2 * profile.getWeight() / (size * size)) + (0.23 * profile.getAge()) - (10.83 * profile.getSex()) - 5.4);
        } else{
            fwi = null;
        }
        profile.setFwi(fwi);
    }

    /**
     * Interprate the value of the FWI and assign the corresponding
     * comment inside the comment field of the profile current instance.
     * @param profile
     */
    private void interprateFWI(Profile profile) {

        Log.d("INFO", "****** interprateFWI ********");
        Integer sex = profile.getSex();
        Float fwi = profile.getFwi();
        String comment;
        int min = sex == 0 ? MIN_WOMAN : MIN_MAN;
        int max = sex == 0 ? MAX_WOMAN : MAX_MAN;
        if(fwi == null){
            comment = "The procees fail to compute your FWI";
        } else if(fwi<0 || fwi>100 ){
            comment = "Invalide data to obtain a useful result ";
        } else if(fwi < min) {
            comment = "Too thin";
        } else if (fwi > min && fwi < max) {
            comment = "Normal";
        } else {
            comment = "Too fat";
        }
        profile.setComment(comment);
    }

    /**
     * Serialize an instance of Profile throught the calling of to the Serialize class.
     * @param profile the instance that we want to be serialize.
     */
    public void serializeProfile(Profile profile){
        Log.d("INFO", "****** serializeProfile ********");
        try {
            Serializer.serialized(this.fileName, profile, this.context);
        } catch(DAOException e){
            e.printStackTrace();
        }
    }

    /**
     * Deserialize an instance the Profile throught the calling of to the Serialize class.
     * @return Profile the profile that has been serialized later.
     */
    public Profile deserializeProfile(){
        Log.d("INFO", "****** deserializeProfile ********");
        Profile profile = null;
        try{
            profile = (Profile) Serializer.deserialized(this.fileName, this.context);
        } catch (DAOException e){
            e.printStackTrace();
        } finally {
            return profile;
        }
    }
}
