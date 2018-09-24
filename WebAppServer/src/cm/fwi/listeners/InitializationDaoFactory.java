package cm.fwi.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cm.fwi.dao.DAOFactory;

/**
 * @author TSIMBO FOKOU
 * @version 1.0
 * Application Lifecycle Listener implementation class InitializationDaoFactory
 * Launch the dao factory and destroy the pooling connection
 *
 */

@WebListener
public class InitializationDaoFactory implements ServletContextListener {

    private static final Logger logger          = Logger.getLogger( "InitializationDaoFactory.class" );
    private static final String ATT_DAO_FACTORY = "daofactory";
    private static DAOFactory   daoFactory;

    /**
     * Launch the daoFactory and as consequence the pooling connection
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized( ServletContextEvent event ) {

        logger.log( Level.INFO, "*****************Launch of the application ******************" );
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServletContext application = event.getServletContext();
        application.setAttribute( ATT_DAO_FACTORY, daoFactory );
        logger.log( Level.INFO, "*****************The application started******************" );
    }

    /**
     * Close the pooling connection.
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed( ServletContextEvent arg0 ) {

        daoFactory.closePool();
        logger.log( Level.INFO, "*****************The pooling connection has been closed ******************" );
        logger.log( Level.INFO, "*****************The application has stoped ******************" );
    }

}
