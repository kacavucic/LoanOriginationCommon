package domain;

import constants.DocumentType;

import javax.sql.rowset.serial.SerialBlob;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Document implements Serializable, GenericEntity {
    private long id;
    private String title;
    private DocumentType type;
    private boolean required;
    private boolean forSigning;
    private byte[] content;
    private String fileName;

    private LoanApplication loanApplication;

    public Document() {
    }

    public Document(long id, String title, DocumentType type, boolean required,
                    boolean forSigning, byte[] content, String fileName,
                    LoanApplication loanApplication) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.required = required;
        this.forSigning = forSigning;
        this.content = content;
        this.fileName = fileName;
        this.loanApplication = loanApplication;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isForSigning() {
        return forSigning;
    }

    public void setForSigning(boolean forSigning) {
        this.forSigning = forSigning;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    @Override
    public String toString() {
        return title;
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
        final Document other = (Document) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "document";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "ClientID, ProductID, LoanApplicationID, DocumentID, Title, Type, Required, ForSigning, Content, FileName";
    }

    // NOT IN USE
    @Override
    public String getInsertValues() {
        try {
            return loanApplication.getClient().getId() + ", " + loanApplication.getProduct().getId() + ", "
                    + loanApplication.getId() + ", " + id + ", '" + title + "', '" + type + "', "
                    + required + ", " + forSigning + ", " + null + ", '" + fileName + "'";

        } catch (Exception ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public String getSelectCondition() {
        if (loanApplication != null) {
            return "LoanApplicationID= " + loanApplication.getId();
        } else {
            return "DocumentID = " + id + " AND LoanApplicationID= " + loanApplication.getId();
        }
    }

    @Override
    public String getDeleteCondition() {
        return "DocumentID = " + id + " AND LoanApplicationID= " + loanApplication.getId();
    }

    @Override
    public String getDeleteConditionForItem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUpdateCondition() {
        return "DocumentID = " + id + " AND LoanApplicationID= " + loanApplication.getId();
    }

    // NOT IN USE
    @Override
    public String setAttributes() {
        try {
            return "ClientID=" + loanApplication.getClient().getId() + ", ProductID=" + loanApplication.getProduct().getId()
                    + ", LoanApplicationID=" + loanApplication.getId() + ", DocumentID=" + id
                    + ", Title='" + title + "', Type='" + type
                    + "', Required=" + required + ", ForSigning=" + forSigning
                    + ", Content=" + new SerialBlob(content);
        } catch (SQLException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public String setAttributesDeactivate() {
        return null;
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {

            Document document = new Document();
            document.setId(rs.getLong("DocumentID"));
            document.setTitle(rs.getString("Title"));
            document.setType(DocumentType.getByName(rs.getString("Type")));
            document.setRequired(rs.getBoolean("Required"));
            document.setForSigning(rs.getBoolean("ForSigning"));
            document.setContent(rs.getBytes("Content"));
            document.setFileName(rs.getString("FileName"));

            LoanApplication la = new LoanApplication();
            la.setId(rs.getLong("LoanApplicationID"));
            document.setLoanApplication(la);

            Client client = new Client();
            client.setId(rs.getLong("ClientID"));
            loanApplication.setClient(client);

            Product product = new Product();
            product.setId(rs.getLong("ProductID"));
            loanApplication.setProduct(product);

            list.add(document);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInsertValuesUnprepared() {
        return "?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        if (content == null) {
            content = "".getBytes();
        }
        statement.setLong(1, loanApplication.getClient().getId());
        statement.setLong(2, loanApplication.getProduct().getId());
        statement.setLong(3, loanApplication.getId());
        statement.setLong(4, id);
        statement.setString(5, title);
        statement.setString(6, type.getName());
        statement.setBoolean(7, required);
        statement.setBoolean(8, forSigning);
        statement.setBlob(9, new SerialBlob(content));
        statement.setString(10, fileName);
    }
}
