package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericEntity extends Serializable {
    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    String getInsertValuesUnprepared();

    void prepareStatement(PreparedStatement statement) throws SQLException;

    // NOT IN USE
    void setID(Long id);

    String getSelectCondition();

    // NOT IN USE
    String getDeleteCondition();

    // NOT IN USE
    String getDeleteConditionForItem();

    String getUpdateCondition();

    String setAttributes();

    String setAttributesDeactivate();

    List<GenericEntity> getList(ResultSet rs) throws Exception;

    String getSpecificSelectCondition();

}
