package dsgc.schema;

import java.util.Objects;

// cluster,namespace,aem_service,references,Size GB
public record DsgcSize(String cluster, String namespace, String aemService, long references, double size) {

    @Override
    public boolean equals(Object o) {
        return o instanceof DsgcSize dsgcSize && Objects.equals(aemService, dsgcSize.aemService);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(aemService);
    }

    @Override
    public String toString() {
        return aemService;
    }
}
