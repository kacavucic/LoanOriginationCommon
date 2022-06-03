package constants;

import java.io.Serializable;

public enum ProductStatus implements Serializable {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String status;

    ProductStatus(String name) {
        status = name;
    }

    @Override
    public String toString() {
        return status;
    }

    public String getName() {
        return status;
    }

    public static ProductStatus getByName(String name) {
        for (ProductStatus s : values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        throw new IllegalArgumentException();
    }
}
