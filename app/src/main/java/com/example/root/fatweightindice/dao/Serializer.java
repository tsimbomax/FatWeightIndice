package com.example.root.fatweightindice.dao;

import android.content.Context;

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


public abstract class Serializer {


    /**
     * This method can be used to serialize any object
     * @param object It represent the object that should be serialize
     * @throws DAOException This exception is generated when an error occurs during the serialize process
     */
    public static void serialized(String fileName, Object object, Context context) throws DAOException {

        FileOutputStream file = null;
        ObjectOutputStream oos = null;

        try{
            //opening the file as a writing mode ie. outputFile
            file = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            //opening of a stream (output stream, as the file corresponding file has been opened as OutputFile) on the file stream 'file'
            oos = new ObjectOutputStream(file);

            // serialization
            oos.writeObject(object);
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
     *This method is used to deserialize any object that has been previously serialized.
     * @return It return the object that have been serialize else null
     * @throws DAOException It throws an exception when an error occurs during the deserialize process
     */
    public static Object deserialized(String fileName, Context context) throws DAOException {

        FileInputStream file = null;
        ObjectInputStream ois = null;
        Object object = null;

        try{
            // opening the stream file
            file = context.openFileInput(fileName);
            // open the object stream
            ois = new ObjectInputStream(file);

            //deserialization
            object = ois.readObject();

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
                return object;
            }
        }
    }

}
