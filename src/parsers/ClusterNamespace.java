package parsers;

import java.util.List;

public class ClusterNamespace {
    private List<String> cluster;
    private List<String> namespace;

    public ClusterNamespace(List<String> cluster, List<String> namespace) {
        this.namespace = namespace;
        this.cluster = cluster;
    }

    public List<String> getCluster() {
        return cluster;
    }

    public List<String> getNamespace() {
        return namespace;
    }

    public void setCluster(List<String> cluster) {
        this.cluster = cluster;
    }

    public void setNamespace(List<String> namespace) {
        this.namespace = namespace;
    }

    @Override
    public String toString() {
        return "parsers.ClusterNamespace{" +
                "clusterId='" + cluster + '\'' +
                ", namespace=" + namespace +
                '}';
    }
}
