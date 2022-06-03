package constants;

import java.io.Serializable;

public enum MaritalStatus implements Serializable {
    NONE("None"),
    MARRIED("Married"),
    WIDOWER("Widower"),
    DIVORCED("Divorced");

    private final String maritalStatus;

    MaritalStatus(String name) {
        maritalStatus = name;
    }

    @Override
    public String toString() {
        return maritalStatus;
    }

    public String getName() {
        return maritalStatus;
    }

    public static MaritalStatus getByName(String name) {
        for (MaritalStatus bs : values()) {
            if (bs.getName().equals(name)) {
                return bs;
            }
        }
        throw new IllegalArgumentException();
    }
}
