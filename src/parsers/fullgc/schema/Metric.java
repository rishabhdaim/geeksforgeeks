package parsers.fullgc.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Metric(@JsonProperty("aem_service") String aemService) {
}
