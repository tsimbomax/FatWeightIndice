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
import java.util.List;


public abstract class Serializer {

    private static final String FILE_NAME = "";

    /**
     * This method can be used to serialize any object
     * @param object It represent the object that should be serialize
     * @throws DAOException This exception is generated when an error occurs during the serialize process
     */
    public void serialized(Object object, Context context) throws DAOException {

        FileOutputStream file = null;
        ObjectOutputStream oos = null;

        try{
            //opening the file as a writing mode ie. outputFile
            file = new FileOutputStream(FILE_NAME);

            //opening of a stream (output stream, as the file corresponding file has been opened as OutputFile) on the file stream 'file'
            oos = new ObjectOutputStream(file);

            // serialization
            oos.writeObject(object);
            oos.flush();


        } catch(FileNotFoundException e){

        } catch(InvalidClassException | NotSerializableException e){

        } catch (IOException e){

        } finally{
            try {
                closeResources(oos, file);
            }catch(IOException e){

            }

        }
    }

    /**
     *This method is used to deserialize any object that has been previously serialized.
     * @return It return the object that have been serialize else null
     * @throws DAOException It throws an exception when an error occurs during the deserialize process
     */
    public Object deserialized() throws DAOException {

        FileInputStream file = null;
        ObjectInputStream ois = null;
        Object object = null;

        try{
            // opening the stream file
            file = new FileInputStream(FILE_NAME);
            // open the object stream
            ois = new ObjectInputStream(file);

            //deserialization
            object = ois.readObject();

        } catch(FileNotFoundException e){

        } catch(InvalidClassException | ClassNotFoundException | StreamCorruptedException | OptionalDataException e){

        } catch (IOException e){

        } finally{
            try {
                closeResources(ois, file);
            }catch(IOException e){

            } finally {
                return object;
            }
        }
    }

}
