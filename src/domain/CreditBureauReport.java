package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditBureauReport implements Serializable, GenericEntity {
    private long id;
    private Date creationDate;
    private double totalDebt;
    private double totalAnnuity;
    private int numberOfDaysInDelay;

    private Client client;

    public CreditBureauReport() {
    }

    public CreditBureauReport(long id, Date creationDate, double totalDebt,
                              double totalAnnuity, int numberOfDaysInDelay, Client client) {
        this.id = id;
        this.creationDate = creationDate;
        this.totalDebt = totalDebt;
        this.totalAnnuity = totalAnnuity;
        this.numberOfDaysInDelay = numberOfDaysInDelay;
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

    public double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public double getTotalAnnuity() {
        return totalAnnuity;
    }

    public void setTotalAnnuity(double totalAnnuity) {
        this.totalAnnuity = totalAnnuity;
    }

    public int getNumberOfDaysInDelay() {
        return numberOfDaysInDelay;
    }

    public void setNumberOfDaysInDelay(int numberOfDaysInDelay) {
        this.numberOfDaysInDelay = numberOfDaysInDelay;
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
        final CreditBureauReport other = (CreditBureauReport) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "credit_bureau_report";
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

            CreditBureauReport creditBureauReport = new CreditBureauReport();
            creditBureauReport.setId(rs.getLong("CreditBureauReportID"));
            creditBureauReport.setCreationDate(rs.getDate("CreationDate"));
            creditBureauReport.setTotalDebt(rs.getDouble("TotalDebt"));
            creditBureauReport.setTotalAnnuity(rs.getDouble("TotalAnnuity"));
            creditBureauReport.setNumberOfDaysInDelay(rs.getInt("NumberOfDaysInDelay"));

            // WTF
            Client c = new Client();
            c.setId(rs.getLong("ClientID"));
            creditBureauReport.setClient(c);

            list.add(creditBureauReport);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
