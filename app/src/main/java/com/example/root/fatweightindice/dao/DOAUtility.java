package com.example.root.fatweightindice.dao;

import android.database.Cursor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class DOAUtility {

    /**
     * Close a file output stream.
     * @param file The file output stream to be created
     * @throws IOException
     */
    public static void closeResource(FileOutputStream file) throws IOException{
        if(file != null)
            file.close();
    }

    public static void closeResource(FileInputStream file) throws IOException{
        if(file != null)
            file.close();
    }

    public static void closeResource(ObjectOutputStream oos) throws IOException{
        if(oos != null)
            oos.close();
    }

    public static void closeResource(ObjectInputStream ois) throws IOException{
        if(ois != null)
            ois.close();
    }

    public static void closeResource(Cursor cursor) {
        if(cursor != null)
            cursor.close();
    }

    public static void closeResources(ObjectOutputStream oos, FileOutputStream file) throws IOException{

        closeResource(oos);
        closeResource(file);
    }

    public static void closeResources(ObjectInputStream ois, FileInputStream file) throws IOException{

        closeResource(ois);
        closeResource(file);
    }
}
