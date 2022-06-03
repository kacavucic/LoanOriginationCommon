package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable, GenericEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User() {
    }

    public User(long id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        final User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "FirstName, LastName, Username, Password";
    }

    @Override
    public String getInsertValues() {
        return "'" + firstName + "', '" + lastName + "', '" + username + "', '" + password + "' ";
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
        if (username != null) {
            return "Username='" + username + "' AND Password='" + password + "'";
        } else {
            return "UserID=" + id;
        }
    }

    @Override
    public String getDeleteCondition() {
        return "UserID=" + id;
    }

    @Override
    public String getDeleteConditionForItem() {
        return null;
    }

    @Override
    public String getUpdateCondition() {
        return "UserID=" + id;
    }

    @Override
    public String setAttributes() {
        return "FirstName='" + firstName + "', LastName='" + lastName + "', Username='"
                + username + "', Password='" + password + "' ";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("UserID"));
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));

            list.add(user);
        }
        return list;
    }

    @Override
    public String setAttributesDeactivate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSpecificSelectCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
