package dope.nathan.application.asbo;

import dope.nathan.application.adapters.LocalDateAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "ClientBO")
@XmlType(propOrder = {"id", "login", "password", "birthDate", "firstName", "lastName"})
public class ClientBO {
    private int id;
    private String login;
    private String password;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;

    public ClientBO() {

    }

    public ClientBO(String login, String password, LocalDate birthDate, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "ClientBO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}