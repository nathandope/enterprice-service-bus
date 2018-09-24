package dope.nathan.application.ejb;

import dope.nathan.application.asbo.ClientBO;
import dope.nathan.application.dao.ClientDAO;
import dope.nathan.application.ejb.impl.ClientManagerEJBLocal;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Local(ClientManagerEJBLocal.class)
public class ClientManagerEJB implements ClientManagerEJBLocal {
    private static final String CLASS_NAME = ClientManagerEJB.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Resource(lookup = "java:/jboss/DataSources/MySqlDS")
    private javax.sql.DataSource mySqlDataSource;

    public ClientManagerEJB() {
    }

    public ClientBO getClientBO(int id) {
        final String METHOD_NAME = "getClientBO";
        logger.entering(CLASS_NAME, METHOD_NAME);

        ClientBO clientBO = null;
        try {
            ClientDAO clientDAO = new ClientDAO(mySqlDataSource);
            clientBO = clientDAO.getClientById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.exiting(CLASS_NAME, METHOD_NAME);
        return clientBO;
    }

    public List<ClientBO> getAllClientBO() {
        final String METHOD_NAME = "getAllClientBO";
        logger.entering(CLASS_NAME, METHOD_NAME);

        List<ClientBO> clientBOList = new ArrayList<ClientBO>();
        try {
            ClientDAO clientDAO = new ClientDAO(mySqlDataSource);
            clientBOList = clientDAO.getAllClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return clientBOList;
    }

    public String addClientBO(ClientBO clientBO) {
		final String METHOD_NAME = "addClientBO";
		logger.entering(CLASS_NAME, METHOD_NAME);

    	String addClientBOStr = "";

    	try {
            ClientDAO clientDAO = new ClientDAO(mySqlDataSource);
            addClientBOStr = clientDAO.addClientToDB(clientBO);
        } catch (Exception e) {
    	    e.printStackTrace();
        }

    	logger.exiting(CLASS_NAME, METHOD_NAME);
    	return addClientBOStr;
    }

    public String editClientBO(ClientBO clientBO) {
        final String METHOD_NAME = "editClientBO";
        logger.entering(CLASS_NAME, METHOD_NAME);

        String editClientBOStr = "";

        try {
            ClientDAO clientDAO = new ClientDAO(mySqlDataSource);
            editClientBOStr = clientDAO.editClientFromDB(clientBO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return editClientBOStr;
    }
}
