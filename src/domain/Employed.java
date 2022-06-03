package domain;


import constants.ClientType;
import constants.ContractType;
import constants.MaritalStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employed extends Client implements Serializable, GenericEntity {
    private String employerName;
    private String taxIdentificationNumberOfEmployer;
    private double salaryAmount;
    private int lengthOfEmployment;
    private ContractType contractType;

    public Employed() {
    }

    public Employed(String employerName, String taxIdentificationNumberOfEmployer,
                    double salaryAmount, int lengthOfEmployment, ContractType contractType,
                    long id, String JMBG, String lastName, String firstName,
                    Date birthDate, String contactPhone, String address,
                    String email, MaritalStatus maritalStatus, ClientType type) {
        super(id, JMBG, lastName, firstName, birthDate, contactPhone, address,
                email, maritalStatus, type);
        this.employerName = employerName;
        this.taxIdentificationNumberOfEmployer = taxIdentificationNumberOfEmployer;
        this.salaryAmount = salaryAmount;
        this.lengthOfEmployment = lengthOfEmployment;
        this.contractType = contractType;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getTaxIdentificationNumberOfEmployer() {
        return taxIdentificationNumberOfEmployer;
    }

    public void setTaxIdentificationNumberOfEmployer(String taxIdentificationNumberOfEmployer) {
        this.taxIdentificationNumberOfEmployer = taxIdentificationNumberOfEmployer;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public int getLengthOfEmployment() {
        return lengthOfEmployment;
    }

    public void setLengthOfEmployment(int lengthOfEmployment) {
        this.lengthOfEmployment = lengthOfEmployment;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String getTableName() {
        return "employed";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "ClientID, EmployerName, TaxIdentificationNumberOfEmployer, SalaryAmount,"
                + " LengthOfEmployment, ContractType";
    }

    @Override
    public String getInsertValues() {
        return super.getId() + ", '" + employerName + "', '" + taxIdentificationNumberOfEmployer + "', "
                + salaryAmount + ", " + lengthOfEmployment + ", '" + contractType + "'";
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
        super.setId(id);
    }

    @Override
    public String getSelectCondition() {
        return "ClientID=" + super.getId();
    }

    @Override
    public String getDeleteCondition() {
        return "ClientID=" + super.getId();
    }

    @Override
    public String getDeleteConditionForItem() {
        return null;
    }

    @Override
    public String getUpdateCondition() {
        return "ClientID=" + super.getId();
    }

    @Override
    public String setAttributes() {
        return "ClientID=" + super.getId() + ", EmployerName='" + employerName + "', TaxIdentificationNumberOfEmployer='" + taxIdentificationNumberOfEmployer
                + "', SalaryAmount=" + salaryAmount + ", LengthOfEmployment=" + lengthOfEmployment
                + ", ContractType='" + contractType + "'";
    }

    @Override
    public String setAttributesDeactivate() {
        return null;
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            Employed employed = new Employed();
            employed.setId(rs.getLong("ClientID"));
            employed.setEmployerName(rs.getString("EmployerName"));
            employed.setTaxIdentificationNumberOfEmployer(rs.getString("TaxIdentificationNumberOfEmployer"));
            employed.setSalaryAmount(rs.getDouble("SalaryAmount"));
            employed.setLengthOfEmployment(rs.getInt("LengthOfEmployment"));
            employed.setContractType(ContractType.getByName(rs.getString("ContractType")));

            list.add(employed);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
