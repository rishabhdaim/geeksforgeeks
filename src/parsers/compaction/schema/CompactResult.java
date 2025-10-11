package parsers.compaction.schema;

public record CompactResult(String aemService, Double originalSize, Double currentSize) {
    @Override
    public String toString() {
        return aemService + "|" + originalSize + "|" + currentSize;
    }
}
