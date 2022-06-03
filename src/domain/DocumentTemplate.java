package domain;

import constants.DocumentType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentTemplate implements Serializable, GenericEntity {
    private long id;
    private String title;
    private DocumentType type;
    private boolean required;
    private boolean forSigning;

    private Product product;

    public DocumentTemplate() {
    }

    public DocumentTemplate(long id, String title, DocumentType type,
                            boolean required, boolean forSigning, Product product) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.required = required;
        this.forSigning = forSigning;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        final DocumentTemplate other = (DocumentTemplate) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "document_template";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "ProductID, DocumentTemplateID, Title, Type, Required, ForSigning";
    }

    @Override
    public String getInsertValues() {
        return product.getId() + ", " + id + ", '"
                + title + "', '" + type + "', " + required + ", " + forSigning;
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
        product.setId(id);
    }

    @Override
    public String getSelectCondition() {
        if (product != null) {
            return "ProductID= " + product.getId();
        } else {
            return "DocumentTemplateID = " + id + " AND ProductID= " + product.getId();
        }
    }

    @Override
    public String getDeleteCondition() {
        return "DocumentTemplateID = " + id + " AND ProductID= " + product.getId();
    }

    @Override
    public String getDeleteConditionForItem() {
        return null;
    }

    @Override
    public String getUpdateCondition() {
        return "DocumentTemplateID = " + id + " AND ProductID= " + product.getId();
    }

    @Override
    public String setAttributes() {
        return "ProductID=" + product.getId() + ", DocumentTemplateID=" + id
                + ", Title='" + title + "', Type='" + type
                + "', Required=" + required
                + ", ForSigning=" + forSigning;
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();

        while (rs.next()) {
            DocumentTemplate documentTemplate = new DocumentTemplate();
            Product p = new Product();

            documentTemplate.setId(rs.getLong("DocumentTemplateID"));
            p.setId(rs.getLong("ProductID"));

            documentTemplate.setProduct(p);

            documentTemplate.setTitle(rs.getString("Title"));
            documentTemplate.setType(DocumentType.getByName(rs.getString("Type")));
            documentTemplate.setRequired(rs.getBoolean("Required"));
            documentTemplate.setForSigning(rs.getBoolean("ForSigning"));

            list.add(documentTemplate);
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
