package parsers.fullgc.schema;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.Objects;

public enum EnvType {
    PROD("prod"), STAGE("stage"), DEV("dev"), RDE("rde"), UNKNOWN("unknown");

    private final String envType;

    EnvType(String envType) {
        this.envType = envType;
    }

    @Nonnull
    public static EnvType fromString(final @Nonnull String envType) {
        if (StringUtils.isBlank(envType)) { return EnvType.UNKNOWN; }
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
