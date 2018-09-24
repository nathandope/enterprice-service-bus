package dope.nathan.application.parser;

import dope.nathan.application.asbo.ClientBO;
import dope.nathan.application.parser.impl.Parser;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

public class JaxbParserTest {
    private Parser parser;
    private File file;

    @Before
    public void setUp() throws Exception {
        parser = new JaxbParser();
        file = new File("clientBO.xml");
    }

    @Test
    public void testGetObject() throws Exception {
        ClientBO clientBO = (ClientBO) parser.getObject(file, ClientBO.class);
        System.out.println(clientBO);
    }

    @Test
    public void testSaveObject() throws Exception {


        ClientBO clientBO = new ClientBO();
        clientBO.setId(33);
        clientBO.setLogin("test_login");
        clientBO.setPassword("test_password");
        clientBO.setBirthDate(LocalDate.now());
        clientBO.setFirstName("test_first_name");
        clientBO.setLastName("test_last_name");

        parser.saveObject(file,clientBO);
    }
}
