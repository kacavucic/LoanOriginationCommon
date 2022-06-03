package constants;

import java.io.Serializable;

public enum DocumentType implements Serializable {
    IDENTIFICATION_DOCUMENT("Identification document"),
    CERTIFICATE_OF_EMPLOYMENT("Certificate of employment"),
    AVERAGE_QUARTERLY_INCOME("Average quarterly income"),
    OTHER("OTHER");

    private final String type;

    DocumentType(String name) {
        type = name;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getName() {
        return type;
    }

    public static DocumentType getByName(String name) {
        for (DocumentType t : values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }
}
