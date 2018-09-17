package com.example.root.fatweightindice.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String sqlDBCreation  = "CREATE TABLE Profile (" +
            "date TEXT PRIMARY KEY," +
            "weight INTEGER NOT NULL," +
            "size INTEGER NOT NULL," +
            "age INTEGER NOT NULL," +
            "sex INTEGER NOT NULL);";

    /**
     * Unique and public constructor
     * @param context to use to locating paths of the database in the application
     * @param name of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version of the database and will be use to upgrade or downgrade the database
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     * @param sqLiteDatabase the SQLite database to create.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) throws SQLException{

        sqLiteDatabase.execSQL(sqlDBCreation);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * @param sqLiteDatabase the SQLite database to upgrade
     * @param oldVersion the old version of the database
     * @param newVersion the new version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
