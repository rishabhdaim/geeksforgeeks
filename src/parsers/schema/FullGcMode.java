package parsers.schema;

import javax.annotation.Nonnull;

public enum FullGcMode {

    NONE("NONE"), GAP_ORPHANS_EMPTYPROPS("GAP_ORPHANS_EMPTYPROPS"), GAP_ORPHANS("GAP_ORPHANS"), EMPTYPROPS("EMPTYPROPS"), ALL_ORPHANS_EMPTYPROPS("ALL_ORPHANS_EMPTYPROPS");

    private final String gcMode;

    FullGcMode(String gcMode) {
        this.gcMode = gcMode;
    }

    @Nonnull
    public static FullGcMode fromString(final @Nonnull String gcMode) {
        for (FullGcMode type : FullGcMode.values()) {
            if (type.gcMode.equalsIgnoreCase(gcMode)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid gc mode: " + gcMode);
    }


    @Override
    public String toString() {
        return gcMode;
    }
}
