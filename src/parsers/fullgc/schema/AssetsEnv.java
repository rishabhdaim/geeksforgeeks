package parsers.fullgc.schema;

import java.util.Objects;

public record AssetsEnv(String id, String atlasClusterId, String kubernetesClusterNamespaceId, String tenantId,
                        String programId, String aemService) {

    @Override
    public boolean equals(Object o) {
        return o instanceof AssetsEnv assetsEnv && Objects.equals(aemService, assetsEnv.aemService);
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
