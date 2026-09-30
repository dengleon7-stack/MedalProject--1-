import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds submitted results and enforces the same validation rules the
 * "Submit Result" button applies in the UI. Kept separate from Main.java
 * so it can be exercised by tests without needing a display.
 */
public class ResultManager {

    public static final List<String> VALID_EVENTS =
            Collections.unmodifiableList(java.util.Arrays.asList("100m", "200m", "Relay"));

    private final List<ResultRecord> results = new ArrayList<>();

    /**
     * Validates and stores a result.
     * Throws IllegalArgumentException with a user-facing message if invalid.
     */
    public ResultRecord addResult(String athlete, String event, String result) {
        String cleanAthlete = athlete == null ? "" : athlete.trim();
        String cleanResult = result == null ? "" : result.trim();

        if (cleanAthlete.isEmpty()) {
            throw new IllegalArgumentException("Athlete name cannot be empty.");
        }
        if (event == null || !VALID_EVENTS.contains(event)) {
            throw new IllegalArgumentException("Event must be one of " + VALID_EVENTS + ".");
        }
        if (cleanResult.isEmpty()) {
            throw new IllegalArgumentException("Result cannot be empty.");
        }

        ResultRecord record = new ResultRecord(cleanAthlete, event, cleanResult);
        results.add(record);
        return record;
    }

    public int getResultCount() {
        return results.size();
    }

    public List<ResultRecord> getResultsForEvent(String event) {
        List<ResultRecord> filtered = new ArrayList<>();
        for (ResultRecord r : results) {
            if (r.getEvent().equals(event)) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    public List<ResultRecord> getAllResults() {
        return Collections.unmodifiableList(results);
    }

    public void clear() {
        results.clear();
    }
}
