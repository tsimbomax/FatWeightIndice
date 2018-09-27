package com.example.root.fatweightindice.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.business.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.root.fatweightindice.dao.DOAUtility.closeResource;

public class ProfileDAOMySQLite implements ProfileDAO {

    private String dbName = "fwiDB.sqlite";
    private Integer dbVersion = 1;
    private MySQLiteOpenHelper dbAccess;
    private SQLiteDatabase dbManagement;

    /**
     * public constructor create an instance of MySQLiteOpenHelper associate to current instance of the class.
     * @param context the one of the application used to specify paths to the database.
     */
    public ProfileDAOMySQLite(Context context) {
        try {
            this.dbAccess = new MySQLiteOpenHelper(context, dbName, null, dbVersion);
        } catch (SQLException e){
           throw new DAOException(e);
        }
    }


    /**
     * Store an instance of Profile inside a MySQLite database
     * @param profile that is going to be store in a MySQLite database
     * @throws DAOException is generated when an error occurs during the storage in a MySQLite database.
     */
    @Override
    public void saveProfile(Profile profile) throws DAOException {
        Log.d("INFO", "****** saveProfile ********");
        try {
            dbManagement = dbAccess.getWritableDatabase();
        } catch (SQLiteException e){
            throw new DAOException(e);
        }
        String req = "INSERT INTO Profile (date, weight, size, age, sex) VALUES ('" + DateUtil.convertDateToString(profile.getDate()) + "', " + profile.getWeight() + ", "
                + profile.getSize() + ", " + profile.getAge() + ", " + profile.getSex() + ");";
        try {
            dbManagement.execSQL(req);
        } catch (SQLException e){
            throw new DAOException("fail to save a new profile in the database", e);
        }
    }

    /**
     * Read an instance of Profile in a MySQLite database, the last stored one.
     * @return Profile the last instance that has been stored in a MySQLite database.
     * @throws DAOException is generated when an error occurs during the reading in a MySQLite database.
     */

    @Override
    public Profile getProfile() throws DAOException {
        Log.d("INFO", "****** getProfile ********");
        dbManagement = dbAccess.getReadableDatabase();
        Cursor cursor = null;
        Profile profile = null;
        String req = "SELECT * FROM Profile;";

        //the selectionArgs is used in case of prepared statement, ie, req with ?; see doc of rawQuery.
        cursor = dbManagement.rawQuery(req, null);
        if(cursor == null){
            throw new DAOException("the process fail to read a profile from the database");
        }
        if(cursor.moveToLast()){
            try {
                profile = map(cursor);
            } catch (ParseException e) {
                throw new DAOException("Error while parsing the date", e);
            }
        }
        closeResource(cursor);
        return profile;
    }

    /**
     * Read all instances of Profile in a MySQLite database
     * @return the list of profile that have been store in a MySQLite database.
     * @throws DAOException is generated when an error occurs during the reading in a MySQLite database.
     */
    @Override
    public List<Profile> getProfiles() throws DAOException {
        Log.d("INFO", "****** getProfiles ********");
        dbManagement = dbAccess.getReadableDatabase();
        Cursor cursor;
        List<Profile> profiles = new ArrayList<>();
        String req = "SELECT * FROM Profile;";

        //the selectionArgs is used in case of prepared statement, ie, req with ?; see doc of rawQuery.
        cursor = dbManagement.rawQuery(req, null);

        while(cursor.moveToNext()){
            try {
                profiles.add(map(cursor));
            } catch (ParseException e) {
                throw new DAOException("Error while parsing the date", e);
            }
        }
        closeResource(cursor);
        return profiles;
    }

    /**
     * Delete a profile in the database from its data's attribute.
     *
     * @param date the attribute date of the profile to delete.
     */
    @Override
    public void deleteProfile(String date) {
        Log.d("INFO", "****** deleteProfile ********");
        try {
            dbManagement = dbAccess.getWritableDatabase();
        } catch (SQLiteException e){
            throw new DAOException(e);
        }
        String req = "DELETE FROM Profile WHERE date="+date+";";
        try {
            dbManagement.execSQL(req);
        } catch (SQLException e){
            throw new DAOException("fail to save a new profile in the database", e);
        }
    }

    /**
     * Map the actual data of the cursor into a profile
     * @param cursor at a readable position
     * @return the associate profile of cursor at its position
     * @throws ParseException when error occurs during the date parse.
     */
    public Profile map(Cursor cursor) throws ParseException{
        Date date;
        Integer weight;
        Integer size;
        Integer age;
        Integer sex;
        Profile profile;

        date = DateUtil.convertStringToDate(cursor.getString(0));
        weight = cursor.getInt(1);
        size = cursor.getInt(2);
        age = cursor.getInt(3);
        sex = cursor.getInt(4);
        profile = new Profile(date, weight, size, age, sex);

        return profile;
    }
}
