package parsers.schema;

import java.util.List;

public record Analysis(String name, String executionTime, List<Object> children) {
}
