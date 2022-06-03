package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FraudReport implements Serializable, GenericEntity {
    private long id;
    private Date creationDate;
    private double blackListRate;
    private double AMLRate;

    private Client client;

    public FraudReport() {
    }

    public FraudReport(long id, Date creationDate, double blackListRate,
                       double AMLRate, Client client) {
        this.id = id;
        this.creationDate = creationDate;
        this.blackListRate = blackListRate;
        this.AMLRate = AMLRate;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getBlackListRate() {
        return blackListRate;
    }

    public void setBlackListRate(double blackListRate) {
        this.blackListRate = blackListRate;
    }

    public double getAMLRate() {
        return AMLRate;
    }

    public void setAMLRate(double AMLRate) {
        this.AMLRate = AMLRate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return creationDate + "";
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
        final FraudReport other = (FraudReport) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "fraud_report";
    }

    @Override
    public String getColumnNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return "ClientID=" + client.getId();
    }

    @Override
    public String getDeleteCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDeleteConditionForItem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUpdateCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String setAttributes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String setAttributesDeactivate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {

            FraudReport fraudReport = new FraudReport();
            fraudReport.setId(rs.getLong("FraudReportID"));
            fraudReport.setCreationDate(rs.getDate("CreationDate"));
            fraudReport.setBlackListRate(rs.getDouble("BlackListRate"));
            fraudReport.setAMLRate(rs.getDouble("AMLRate"));

            // WTF
            Client c = new Client();
            c.setId(rs.getLong("ClientID"));
            fraudReport.setClient(c);

            list.add(fraudReport);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
