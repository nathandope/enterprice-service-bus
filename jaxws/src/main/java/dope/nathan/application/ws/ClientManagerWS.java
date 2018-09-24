package dope.nathan.application.ws;



import dope.nathan.application.asbo.ClientBO;
import dope.nathan.application.ejb.impl.ClientManagerEJBLocal;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.logging.Logger;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ClientManagerWS {
    private static final String CLASS_NAME = ClientManagerWS.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @EJB
    private ClientManagerEJBLocal clientManagerEJBLocal;

    @WebMethod
    public String getClientBO(@WebParam(name = "id") int id) {
        final String METHOD_NAME = "getClientBO";
        logger.entering(CLASS_NAME, METHOD_NAME);

        String getClientBOResultStr = "";
        ClientBO clientBO = clientManagerEJBLocal.getClientBO(id);

        if (clientBO != null) {
            getClientBOResultStr = clientBO.toString();
        } else {
            getClientBOResultStr = "Client with id " + id + " was not found!";
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return getClientBOResultStr;
    }
}
