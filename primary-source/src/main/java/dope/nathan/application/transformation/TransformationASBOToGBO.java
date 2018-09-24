package dope.nathan.application.transformation;

import dope.nathan.application.asbo.ClientBO;
import dope.nathan.application.gbo.ClientGBO;

import java.time.LocalDate;

public class TransformationASBOToGBO {
    public ClientGBO transformClient(ClientBO clientBO) {
        ClientGBO clientGBO = new ClientGBO();
        clientGBO.setId(clientBO.getId());
        clientGBO.setLogin(clientBO.getLogin());
        clientGBO.setPassword(clientBO.getPassword());
        clientGBO.setAge(getAge(clientBO.getBirthDate(), LocalDate.now()));
        clientGBO.setFirstName(clientBO.getFirstName());
        clientGBO.setLastName(clientBO.getLastName());
        return clientGBO;
    }

    private int getAge(LocalDate birthDate, LocalDate currentDate) {
        return birthDate.until(currentDate).getYears();
    }
}
