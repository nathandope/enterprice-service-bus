package dope.nathan.application.ejb.impl;

import dope.nathan.application.asbo.ClientBO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClientManagerEJBLocal {
    ClientBO getClientBO(int id);
    List<ClientBO> getAllClientBO();
    String addClientBO(ClientBO clientBO);
    String editClientBO(ClientBO clientBO);
}
