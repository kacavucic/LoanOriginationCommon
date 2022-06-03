package constants;

import java.io.Serializable;

public enum ContractType implements Serializable {
    FIXED_TERM("Fixed-term"),
    INDEFINITE_PERIOD("Indefinite period");

    private final String type;

    ContractType(String name) {
        type = name;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getName() {
        return type;
    }

    public static ContractType getByName(String name) {
        for (ContractType t : values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }
}
