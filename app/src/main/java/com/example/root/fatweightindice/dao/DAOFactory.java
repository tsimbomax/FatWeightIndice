package com.example.root.fatweightindice.dao;

import android.content.Context;

public class DAOFactory {

    private static DAOFactory daoFactory = null;

    /**
     * private and unique constructor.
     */
    private DAOFactory(){
        super();
    }

    /**
     * static method make sure that there will be only one instance of the DAOFactory.
     * @return the unique instance of the DAOFactory.
     */
    public static DAOFactory getInstance(){
        if(daoFactory!=null)
            return daoFactory;
        daoFactory = new DAOFactory();
        return daoFactory;
    }

    /**
     *The the contract of ProfileDAO that the business object will need to access to the database.
     * @param context the one of the application used to locating paths to the database.
     * @return the contract of the ProfileDAO.
     */
    public ProfileDAO getProfileDAO(Context context){

        //return new ProfileDAOMySQLite(context);
        return new ProfileDAOExtDB(context);
    }

    /**
     *The the contract of ProfileDAO that the business object will need to access to the database.
     * @param fileName the name of the file where the object is going to be serialized/deserialized
     * @param context the one of the application used to locating paths to the database.
     * @return the contract of the ProfileDAO.
     */
    public ProfileDAO getProfileDAO(String fileName, Context context){
        return new ProfileDAOSerialize(fileName, context);
    }

}
