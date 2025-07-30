package parsers.fullgc.schema;

import javax.annotation.Nonnull;

public enum EnvType {
    PROD("prod"), STAGE("stage"), DEV("dev"), RDE("rde"), UNKNOWN("unknown");

    private final String envType;

    EnvType(String envType) {
        this.envType = envType;
    }

    @Nonnull
    public static EnvType fromString(final @Nonnull String envType) {
        for (EnvType type : EnvType.values()) {
            if (type.envType.equalsIgnoreCase(envType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid env type: " + envType);
    }


    @Override
    public String toString() {
        return envType;
    }
}
