package constants;

import java.io.Serializable;

public enum LoanApplicationStatus implements Serializable {
    APPROVED("Approved"),
    REJECTED("Rejected"),
    ON_HOLD("On hold");

    private final String status;

    LoanApplicationStatus(String name) {
        status = name;
    }

    @Override
    public String toString() {
        return status;
    }

    public String getName() {
        return status;
    }

    public static LoanApplicationStatus getByName(String name) {
        for (LoanApplicationStatus s : values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        throw new IllegalArgumentException();
    }
}
