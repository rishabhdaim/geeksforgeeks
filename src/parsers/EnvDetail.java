package parsers;

import java.util.Objects;

public record EnvDetail(String id, String name, String type, String release, String envId, String authorUrl,
                        String publishUrl, String previewUrl, String date, String cluster, String namespace,
                        String notes) {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof EnvDetail envDetail && Objects.equals(envId, envDetail.envId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(envId);
    }

    @Override
    public String toString() {
        return cluster + " " + namespace + " " + envId + " " + type;
    }
}
