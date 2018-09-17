package com.example.root.fatweightindice.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.root.fatweightindice.bean.Profile;

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
        String req = "INSERT INTO Profile (date, weight, size, age, sex) VALUES ('" + profile.getDate() + "', " + profile.getWeight() + ", "
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
            Date date = new Date();
            Integer weight = cursor.getInt(1);
            Integer size = cursor.getInt(2);
            Integer age = cursor.getInt(3);
            Integer sex = cursor.getInt(4);
            profile = new Profile(date, weight, size, age, sex);
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

        Date date;
        Integer weight;
        Integer size;
        Integer age;
        Integer sex;
        Profile profile;

        while(cursor.moveToNext()){
            date = new Date(cursor.getString(1));
            weight = cursor.getInt(2);
            size = cursor.getInt(3);
            age = cursor.getInt(4);
            sex = cursor.getInt(5);
            profile = new Profile(date, weight, size, age, sex);
            profiles.add(profile);
        }
        closeResource(cursor);
        return profiles;
    }
}