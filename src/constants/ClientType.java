package constants;

import java.io.Serializable;

public enum ClientType implements Serializable {
    STUDENT("Student"),
    EMPLOYED("Employed");

    private final String type;

    ClientType(String name) {
        type = name;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getName() {
        return type;
    }

    public static ClientType getByName(String name) {
        for (ClientType t : values()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }
}
