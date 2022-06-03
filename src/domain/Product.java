package domain;

import constants.Currency;
import constants.ProductStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable, GenericEntity {
    private long id;
    private String name;
    private String description;
    private Currency currency;
    private double minAmount;
    private double maxAmount;
    private double interestRate;
    private ProductStatus status;

    private List<DocumentTemplate> documentTemplates;

    public Product() {
        //documentTemplates = new ArrayList<>();
    }

    public Product(long id, String name, String description, Currency currency,
                   double minAmount, double maxAmount, double interestRate,
                   ProductStatus status, List<DocumentTemplate> documentTemplates) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.interestRate = interestRate;
        this.status = status;
        this.documentTemplates = documentTemplates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<DocumentTemplate> getDocumentTemplates() {
        return documentTemplates;
    }

    public void setDocumentTemplates(List<DocumentTemplate> documentTemplates) {
        this.documentTemplates = documentTemplates;
    }

    @Override
    public String toString() {
        return name;
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
        final Product other = (Product) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "product";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "Name, Description, Currency, MinAmount, MaxAmount, InterestRate, Status ";
    }

    @Override
    public String getInsertValues() {
        return "'" + name + "', '" + description + "', '" + currency.getName() + "', " + minAmount + ", "
                + maxAmount + ", " + interestRate + ", '" + status.getName() + "'";
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
        return "ProductID=" + id;
    }

    @Override
    public String getDeleteCondition() {
        return "ProductID=" + id;

    }

    @Override
    public String getDeleteConditionForItem() {
        return null;
    }

    @Override
    public String getUpdateCondition() {
        return "ProductID=" + id;

    }

    @Override
    public String setAttributes() {
        return "Name='" + name + "', Description='" + description + "', Currency='" + currency.getName()
                + "', MinAmount=" + minAmount + ", MaxAmount=" + maxAmount
                + ", InterestRate=" + interestRate + ", Status='" + status + "'";
    }

    @Override
    public String setAttributesDeactivate() {
        return "Status = '" + status + "'";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();

            product.setId(rs.getLong("ProductID"));
            product.setName(rs.getString("Name"));
            product.setDescription(rs.getString("Description"));
            product.setCurrency(Currency.getByName(rs.getString("Currency")));
            product.setMinAmount(rs.getDouble("MinAmount"));
            product.setMaxAmount(rs.getDouble("MaxAmount"));
            product.setInterestRate(rs.getDouble("InterestRate"));
            product.setStatus(ProductStatus.getByName(rs.getString("Status")));

            list.add(product);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        String condition = "";
        if (currency != null || status != null) {
            if (currency != null && status == null) {
                condition += "Currency = '" + currency + "'";
            }
            if (currency == null && status != null) {
                condition += "Status = '" + status + "'";
            }
            if (currency != null && status != null) {
                condition += "Currency = '" + currency + "' AND Status = '" + status + "'";
            }

            return condition;
        } else {
            // don't filter products at all in this case
            // throw new Exception("Search criteria not specified!");
            return "1=1";
        }

    }

}
