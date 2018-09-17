package com.example.root.fatweightindice.dao;

import android.content.Context;

import com.example.root.fatweightindice.bean.Profile;

import static com.example.root.fatweightindice.dao.DOAUtility.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.List;


public class ProfileDAOSerialize implements ProfileDAO {


    private String fileName = null;
    private Context context = null;

    /**
     * public constructor, instanced an implementation of the ProfileDao interface.
     * @param fileName the name of the file where the object is going to be serialized/deserialized
     * @param context the one of the application used to locating paths to the database.
     */
    public ProfileDAOSerialize(String fileName, Context context){

        this.fileName = fileName;
        this.context = context;
    }

    /**
     * This method can be used to serialize a Profile's instance.
     * @param profile the instance of Profile that should be serialize
     * @throws DAOException This exception is generated when an error occurs during the serialize process
     */
    @Override
    public void saveProfile(Profile profile) throws DAOException {

        FileOutputStream file = null;
        ObjectOutputStream oos = null;

        try{
            //opening the file as a writing mode ie. outputFile
            file = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            //opening of a stream (output stream, as the file corresponding file has been opened as OutputFile) on the file stream 'file'
            oos = new ObjectOutputStream(file);

            // serialization
            oos.writeObject(profile);
            oos.flush();


        } catch(FileNotFoundException e){
            throw new DAOException("The file " + fileName + " was not found", e);
        } catch(InvalidClassException | NotSerializableException e){
            throw new DAOException("The process fail to serialize the given object", e);
        } catch (IOException e){
            throw new DAOException("An error occurs during the serialization", e);
        } finally{
            try {
                closeResources(oos, file);
            }catch(IOException e){
                throw new DAOException("The process fail to close the used resources", e);
            }

        }
    }

    /**
     *This method is used to deserialize any profile that has been previously serialized.
     * @return It return the Profile's instance that have been serialize else null
     * @throws DAOException It throws an exception when an error occurs during the deserialize process
     */
    @Override
    public Profile getProfile() throws DAOException {

        FileInputStream file = null;
        ObjectInputStream ois = null;
        Profile profile = null;

        try{
            // opening the stream file
            file = context.openFileInput(fileName);
            // open the object stream
            ois = new ObjectInputStream(file);

            //deserialization
            profile = (Profile)ois.readObject();

        } catch(FileNotFoundException e){
            throw new DAOException("The file " + fileName + " was not found", e);
        } catch(InvalidClassException | ClassNotFoundException | StreamCorruptedException | OptionalDataException e){
            throw new DAOException("The process fail to deserialize the given object", e);
        } catch (IOException e){
            throw new DAOException("An error occurs during the deserialization", e);
        } finally{
            try {
                closeResources(ois, file);
            }catch(IOException e){
                throw new DAOException("The process fail to close the used resources", e);
            } finally {
                return profile;
            }
        }
    }

    /**
     * Read all instances of Profile in the serialization file(s). It has not yet been implemented.
     * @return the list of profile that have been serialized.
     * @throws DAOException is generated when an error occurs during the reading in the file of serialization.
     */
    @Override
    public List<Profile> getProfiles() throws DAOException{
        return null;
    }

}
