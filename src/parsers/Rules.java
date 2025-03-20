package parsers;

import java.util.List;

public class Rules {

    private List<ClusterNamespace> clusterNamespaceList;

    @Override
    public String toString() {
        return "parsers.Rules{" +
                "clusterNamespaceList=" + clusterNamespaceList +
                '}';
    }

    public void setClusterNamespaceList(List<ClusterNamespace> clusterNamespaceList) {
        this.clusterNamespaceList = clusterNamespaceList;
    }

    public List<ClusterNamespace> getClusterNamespaceList() {
        return clusterNamespaceList;
    }

    public Rules(List<ClusterNamespace> clusterNamespaceList) {
        this.clusterNamespaceList = clusterNamespaceList;
    }
}
