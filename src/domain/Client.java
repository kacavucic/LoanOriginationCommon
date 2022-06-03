package domain;


import constants.ClientType;
import constants.MaritalStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client implements Serializable, GenericEntity {
    private long id;
    private String JMBG;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String contactPhone;
    private String address;
    private String email;
    private MaritalStatus maritalStatus;
    private ClientType type;

    public Client() {
    }

    public Client(long id, String JMBG, String lastName, String firstName,
                  Date birthDate, String contactPhone, String address,
                  String email, MaritalStatus maritalStatus, ClientType type) {
        this.id = id;
        this.JMBG = JMBG;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.contactPhone = contactPhone;
        this.address = address;
        this.email = email;
        this.maritalStatus = maritalStatus;
        this.type = type;
    }

    public void copyClient(Client c) {
        this.id = c.id;
        this.JMBG = c.JMBG;
        this.lastName = c.lastName;
        this.firstName = c.firstName;
        this.birthDate = c.birthDate;
        this.contactPhone = c.contactPhone;
        this.address = c.address;
        this.email = c.email;
        this.maritalStatus = c.maritalStatus;
        this.type = c.type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "client";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "JMBG, FirstName, LastName, BirthDate, ContactPhone, Address, Email, MaritalStatus, Type";
    }

    @Override
    public String getInsertValues() {
        return "'" + JMBG + "', '" + firstName + "', '" + lastName + "', '"
                + new java.sql.Date(birthDate.getTime()) + "', '"
                + contactPhone + "', '" + address + "', '" + email + "', '" + maritalStatus
                + "', '" + type + "'";
    }

    @Override
    public String getInsertValuesUnprepared() {
        return null;
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {

    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public String getSelectCondition() {
        return "ClientID=" + id;
    }

    @Override
    public String getDeleteCondition() {
        return "ClientID=" + id;
    }

    @Override
    public String getDeleteConditionForItem() {
        return null;
    }

    @Override
    public String getUpdateCondition() {
        return "ClientID=" + id;
    }

    @Override
    public String setAttributes() {
        return "JMBG='" + JMBG + "', FirstName='" + firstName + "', LastName='" + lastName
                + "', BirthDate='" + new java.sql.Date(birthDate.getTime())
                + "', ContactPhone='" + contactPhone
                + "', Address='" + address + "', Email='" + email + "', MaritalStatus='" + maritalStatus
                + "', Type='" + type + "'";
    }

    @Override
    public String setAttributesDeactivate() {
        return null;
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            Client client = new Client();
            client.setId(rs.getLong("ClientID"));
            client.setJMBG(rs.getString("JMBG"));
            client.setFirstName(rs.getString("FirstName"));
            client.setLastName(rs.getString("LastName"));
            client.setBirthDate(rs.getDate("BirthDate"));
            client.setContactPhone(rs.getString("ContactPhone"));
            client.setAddress(rs.getString("Address"));
            client.setEmail(rs.getString("Email"));
            client.setMaritalStatus(MaritalStatus.getByName(rs.getString("MaritalStatus")));
            client.setType(ClientType.getByName(rs.getString("Type")));

            list.add(client);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        String condition = "";
        if (!lastName.isEmpty() || !JMBG.isEmpty()) {
            if (!lastName.isEmpty() && JMBG.isEmpty()) {
                condition += "LastName LIKE '" + lastName + "%'";
            }
            if (lastName.isEmpty() && !JMBG.isEmpty()) {
                condition += "JMBG LIKE '" + JMBG + "%'";
            }
            if (!lastName.isEmpty() && !JMBG.isEmpty()) {
                condition += "LastName LIKE '" + lastName + "%' AND JMBG LIKE '" + JMBG + "%'";
            }
            return condition;
        } else {
            return "1=1";
        }
    }
}
