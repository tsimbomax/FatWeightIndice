package cm.fwi.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author TSIMBO FOKOU
 * @version 1.0
 * Servlet Filter implementation class CheckParam
 * Filter the well parametric request from the client.
 */
@WebFilter("/fwi/profile")
public class CheckParam implements Filter {
    
    private static final Logger logger          = Logger.getLogger( "CheckParam.class" );
    private static final String OPERATION = "operation";

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	    logger.log( Level.INFO, "*****************Launch of the filter CheckParam ******************" );
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    logger.log( Level.INFO, "***************** Filter : checking parameter ******************" );
	    String operation = request.getParameter( OPERATION );
	    
	    if(operation == null || operation.isEmpty()) {
	        logger.log( Level.INFO, "***************** the request has been rejected ******************" );
	        return;
	    }
	    logger.log( Level.INFO, "***************** The reqeust has been accepted ******************" );
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	    logger.log( Level.INFO, "*****************Launch of the filter CheckParam ******************" );
	}

}
