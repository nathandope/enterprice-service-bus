package dope.nathan.application.dao;

import dope.nathan.application.asbo.ClientBO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class ClientDAO {
    private static final String CLASS_NAME = ClientDAO.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private DataSource dataSource;

    private final String SELECT_FROM_CLIENTS_BY_ID =
            "SELECT" +
                    " clnt_id" +
                    ", clnt_login" +
                    ", clnt_password" +
                    ", clnt_birthdate" +
                    ", clnt_firstname" +
                    ", clnt_lastname" +
                    " FROM clients" +
                    " WHERE clnt_id=?";

    private final String SELECT_FROM_CLIENTS_ALL =
            "SELECT" +
                    " clnt_id" +
                    ", clnt_login" +
                    ", clnt_password" +
                    ", clnt_birthdate" +
                    ", clnt_firstname" +
                    ", clnt_lastname" +
                    " FROM clients";

    private final String UPDATE_CLIENTS_BY_ID =
            "UPDATE clients " +
                    "SET" +
                    " clnt_login=?" +
                    ", clnt_password=?" +
                    ", clnt_birthdate=?" +
                    ", clnt_firstname=?" +
                    ", clnt_lastname=?" +
                    " WHERE clnt_id=?";

    private final String INSERT_INTO_CLIENTS =
            "INSERT INTO clients" +
                    " (clnt_login" +
                    ", clnt_password" +
                    ", clnt_birthdate" +
                    ", clnt_firstname" +
                    ", clnt_lastname)" +
                    " VALUES (?, ?, ?, ?, ?)";

    public ClientDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ClientBO getClientById(int id) throws SQLException {
        final String METHOD_NAME = "getClientById";
        logger.entering(CLASS_NAME, METHOD_NAME);

        ClientBO clientFromDB = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preStmt = connection.prepareStatement(SELECT_FROM_CLIENTS_BY_ID);
        preStmt.setInt(1, id);
        ResultSet rs = preStmt.executeQuery();

        while (rs.next()) {
            clientFromDB = new ClientBO();
            clientFromDB.setId(rs.getInt("clnt_id"));
            clientFromDB.setLogin(rs.getString("clnt_login"));
            clientFromDB.setPassword(rs.getString("clnt_password"));
            clientFromDB.setBirthDate(rs.getDate("clnt_birthdate").toLocalDate());
            clientFromDB.setFirstName(rs.getString("clnt_firstname"));
            clientFromDB.setLastName(rs.getString("clnt_lastname"));
        }

        rs.close();
        preStmt.close();
        connection.close();

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return clientFromDB;
    }

    public List<ClientBO> getAllClient() throws SQLException {
        final String METHOD_NAME = "getAllClient";
        logger.entering(CLASS_NAME, METHOD_NAME);

        List<ClientBO> allClientFromDB = new LinkedList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preStmt = connection.prepareStatement(SELECT_FROM_CLIENTS_ALL);
        ResultSet rs = preStmt.executeQuery();

        while (rs.next()) {
            ClientBO clientFromDB = new ClientBO();
            clientFromDB.setId(rs.getInt("clnt_id"));
            clientFromDB.setLogin(rs.getString("clnt_login"));
            clientFromDB.setPassword(rs.getString("clnt_password"));
            clientFromDB.setBirthDate(rs.getDate("clnt_birthdate").toLocalDate());
            clientFromDB.setFirstName(rs.getString("clnt_firstname"));
            clientFromDB.setLastName(rs.getString("clnt_lastname"));

            allClientFromDB.add(clientFromDB);
        }

        rs.close();
        preStmt.close();
        connection.close();

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return allClientFromDB;
    }

    public String editClientFromDB(ClientBO clientBO) throws SQLException {
        final String METHOD_NAME = "editClientFromDB";
        logger.entering(CLASS_NAME, METHOD_NAME);

        String addResultStr = "Информация о клиенте изменена успешно.";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(UPDATE_CLIENTS_BY_ID);
            preStmt.setString(1, clientBO.getLogin());
            preStmt.setString(2, clientBO.getPassword());

            System.out.println(clientBO.getBirthDate());
            System.out.println(Date.valueOf(clientBO.getBirthDate()));

            preStmt.setDate(3, Date.valueOf(clientBO.getBirthDate()));
            preStmt.setString(4, clientBO.getFirstName());
            preStmt.setString(5, clientBO.getLastName());
            preStmt.setInt(6, clientBO.getId());

            int resultSTMT = preStmt.executeUpdate();
            if (resultSTMT == 0) {addResultStr = "Произошла ошибка во время изменения информации о клиенте.";}

            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Произошла ошибка во время изменения информации о клиенте.");
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return addResultStr;
    }

    public String addClientToDB(ClientBO clientBO) throws SQLException {
        final String METHOD_NAME = "addClientToDB";
        logger.entering(CLASS_NAME, METHOD_NAME);

        String addResultStr = "Клиент добавлен успешно.";

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preStmt = connection.prepareStatement(INSERT_INTO_CLIENTS);
            preStmt.setString(1, clientBO.getLogin());
            preStmt.setString(2, clientBO.getPassword());
            preStmt.setDate(3, Date.valueOf(clientBO.getBirthDate()));
            preStmt.setString(4, clientBO.getFirstName());
            preStmt.setString(5, clientBO.getLastName());

            int resultSTMT = preStmt.executeUpdate();
            if (resultSTMT == 0) {addResultStr = "Произошла ошибка во время добавления клиента.";}

            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Произошла ошибка во время добавления клиента.");
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return addResultStr;
    }
}
