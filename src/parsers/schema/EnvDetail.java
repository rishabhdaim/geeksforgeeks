package parsers.schema;

import java.util.Objects;

public record EnvDetail(String id, String name, String type, String release, String envId,
                        String cluster, String namespace
) {

    public EnvDetail(String type, String envId, String cluster, String namespace) {
        this(null, null, type, null, envId, cluster, namespace);
    }


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
