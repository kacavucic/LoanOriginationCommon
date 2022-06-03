package domain;

import constants.LoanApplicationStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanApplication implements Serializable, GenericEntity {
    private long id;
    private Date creationDate;
    private LoanApplicationStatus status;
    private double amount;
    private double annuity;
    private int repaymentPeriod;

    private Client client;
    private Product product;
    private CreditBureauReport creditBureauReport;
    private FraudReport fraudReport;
    private List<Document> documents;

    public LoanApplication() {
    }

    public LoanApplication(long id, Date creationDate,
                           LoanApplicationStatus status, double amount, double annuity,
                           int repaymentPeriod, Client client, Product product,
                           CreditBureauReport creditBureauReport, FraudReport fraudReport,
                           List<Document> documents) {
        this.id = id;
        this.creationDate = creationDate;
        this.status = status;
        this.amount = amount;
        this.annuity = annuity;
        this.repaymentPeriod = repaymentPeriod;
        this.client = client;
        this.product = product;
        this.creditBureauReport = creditBureauReport;
        this.fraudReport = fraudReport;
        this.documents = documents;
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

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(LoanApplicationStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAnnuity() {
        return annuity;
    }

    public void setAnnuity(double annuity) {
        this.annuity = annuity;
    }

    public int getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(int repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CreditBureauReport getCreditBureauReport() {
        return creditBureauReport;
    }

    public void setCreditBureauReport(CreditBureauReport creditBureauReport) {
        this.creditBureauReport = creditBureauReport;
    }

    public FraudReport getFraudReport() {
        return fraudReport;
    }

    public void setFraudReport(FraudReport fraudReport) {
        this.fraudReport = fraudReport;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
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
        final LoanApplication other = (LoanApplication) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "loan_application";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "ClientID, ProductID, CreationDate, Status, Amount, Annuity, RepaymentPeriod";
    }

    @Override
    public String getInsertValues() {
        return client.getId() + ", " + product.getId() + ", '" + new java.sql.Date(creationDate.getTime()) + "', '" + status + "', "
                + amount + ", " + annuity + ", " + repaymentPeriod;
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
        return "LoanApplicationID=" + id;
    }

    @Override
    public String getDeleteCondition() {
        return "LoanApplicationID=" + id;
    }

    @Override
    public String getDeleteConditionForItem() {
        return null;
    }

    @Override
    public String getUpdateCondition() {
        return "LoanApplicationID=" + id;
    }

    @Override
    public String setAttributes() {
        return "Status='" + status + "', CreditBureauReportID=" + creditBureauReport.getId() + ", FraudReportID=" + fraudReport.getId();
    }

    @Override
    public String setAttributesDeactivate() {
        return null;
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            LoanApplication loanApplication = new LoanApplication();
            loanApplication.setId(rs.getLong("LoanApplicationID"));
            loanApplication.setCreationDate(rs.getDate("CreationDate"));
            loanApplication.setStatus(LoanApplicationStatus.getByName(rs.getString("Status")));
            loanApplication.setAmount(rs.getDouble("Amount"));
            loanApplication.setAnnuity(rs.getDouble("Annuity"));
            loanApplication.setRepaymentPeriod(rs.getInt("RepaymentPeriod"));

            Client client = new Client();
            client.setId(rs.getLong("ClientID"));
            loanApplication.setClient(client);

            Product product = new Product();
            product.setId(rs.getLong("ProductID"));
            loanApplication.setProduct(product);

            CreditBureauReport creditBureauReport = new CreditBureauReport();
            creditBureauReport.setId(rs.getLong("CreditBureauReportID"));
            creditBureauReport.setClient(client);
            loanApplication.setCreditBureauReport(creditBureauReport);

            FraudReport fraudReport = new FraudReport();
            fraudReport.setId(rs.getLong("FraudReportID"));
            fraudReport.setClient(client);
            loanApplication.setFraudReport(fraudReport);

            list.add(loanApplication);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        String condition = "";

        if (status != null) {
            condition += "Status = '" + status + "'";
            return condition;
        } else {
            // don't filter products at all in this case
            // throw new Exception("Search criteria not specified!");
            return "1=1";
        }
    }

}
