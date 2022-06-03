package constants;

import java.io.Serializable;

public enum Currency implements Serializable {
    EUR("EUR"),
    RSD("RSD"),
    USD("USD");

    private final String currency;

    Currency(String name) {
        currency = name;
    }

    @Override
    public String toString() {
        return currency;
    }

    public String getName() {
        return currency;
    }

    public static Currency getByName(String name) {
        for (Currency v : values()) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
