package parsers.schema;

import java.util.List;

public record Data(String resultType, List<Result> result, Analysis analysis) {
}
