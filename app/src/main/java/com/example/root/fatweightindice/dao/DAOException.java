package com.example.root.fatweightindice.dao;

public class DAOException extends RuntimeException {

    /**
     *
     * @param message
     */
    public DAOException(String message){
        super(message);
    }

    /**
     *
     * @param cause
     */
    public DAOException(Throwable cause){
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause){
        super(message, cause);
    }
}
