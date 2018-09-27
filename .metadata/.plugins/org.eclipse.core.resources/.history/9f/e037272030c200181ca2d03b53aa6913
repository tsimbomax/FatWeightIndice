package cm.fwi.business;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class UtilBus {

    private Map<String, String> errors = new HashMap<String, String>();
    private String              result = "";

    public void setErrors( String parName, String parValue ) {
        errors.put( parName, parValue );
    }

    public void setErrors( Map<String, String> errors ) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public void setResult( String result ) {
        this.result = result;
    }

    public String valueParam( HttpServletRequest request, String parName ) {

        String value = request.getParameter( parName );
        if ( value == null || value.trim().isEmpty() ) {
            return null;
        } else {
            return value.trim();
        }
    }

/*    public String valueParamMPF( HttpServletRequest request, String parName ) throws BusValidationException {

        String paramValue = null;
        Part part = null;
        UploadBus upload = new UploadBus();
        try {
            part = request.getPart( parName );
            paramValue = upload.getValue( part );
            System.out.println( "MPD-" + paramValue );
        } catch ( IOException | ServletException e ) {
            throw new BusValidationException( "The Process fail reaad the part associate to the " + parName + " field",
                    e );
        } finally {
            return paramValue;
        }
    }*/

    public Boolean isLetter( char letter ) {

        if ( !( letter >= 'a' && letter <= 'z' ) && !( letter >= 'A' && letter <= 'Z' ) ) {
            return false;
        }
        return true;
    }

    public Boolean isEmptyErrors() {

        return errors.isEmpty() ? true : false;
    }
}
