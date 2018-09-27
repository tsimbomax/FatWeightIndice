package cm.fwi.dao;

import java.util.List;

import cm.fwi.beans.Profile;

public interface ProfileDao {

    /**
     * 
     * @param profile
     * @throws DAOException
     */
    void createProfile( Profile profile ) throws DAOException;

    /**
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    Profile readProfile( Long id ) throws DAOException;

    /**
     * 
     * @return
     * @throws DAOException
     */
    List<Profile> listOfProfiles() throws DAOException;

    /**
     * Delete a profile from the databse
     * @param id of the profile to delete
     * @throws DAOException
     */
    void deleteProfile( String date) throws DAOException;
}
