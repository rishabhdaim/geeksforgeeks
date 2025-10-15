package parsers.compaction.schema;

public record Env(String cluster, String namespace, String aemService) {
    @Override
    public String toString() {
        return cluster + " " + namespace + " " + aemService;
    }
}
