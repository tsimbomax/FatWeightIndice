package com.example.root.fatweightindice.dao;

import com.example.root.fatweightindice.bean.Profile;

import java.util.List;

public interface ProfileDAO {

    /**
     * Store an instance of Profile inside a database
     * @param profile that is going to be store in the database
     * @throws DAOException is generated when an error occurs during the storage in the database.
     */
    public void saveProfile(Profile profile) throws DAOException;

    /**
     * Read an instance of Profile in the database, the last stored one.
     * @return Profile the last instance that has been stored in the database.
     * @throws DAOException is generated when an error occurs during the reading in the database.
     */
    public Profile getProfile() throws DAOException;

    /**
     * Read all instances of Profile in the database
     * @return the list of profile that have been store in the database.
     * @throws DAOException is generated when an error occurs during the reading in the database.
     */
    public List<Profile> getProfiles() throws DAOException;

    /**
     * Delete a profile in the database from its data's attribute.
     * @param date the attribute date of the profile to delete.
     */
    public void deleteProfile(String date);
}
