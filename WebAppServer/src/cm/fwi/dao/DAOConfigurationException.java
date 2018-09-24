package cm.fwi.dao;

public class DAOConfigurationException extends RuntimeException {

    /**
     * Contructor with message set.
     * @param message
     */
    public DAOConfigurationException( String message ) {
        super( message );
    }

    /**
     *  Contructor with message a throwable set
     * @param message
     * @param cause
     */
    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     *  Contructor with throwable set
     * @param cause
     */
    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
