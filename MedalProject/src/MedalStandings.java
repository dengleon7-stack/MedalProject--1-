import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks gold-medal counts per country and produces a standings list
 * sorted from most to fewest golds. Replaces the hardcoded
 * "USA/12, China/10, Kenya/8" JLabels from the original screenshot with
 * data that can be changed and verified programmatically.
 */
public class MedalStandings {

    private final Map<String, Integer> golds = new LinkedHashMap<>();

    public void addCountry(String country, int goldCount) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be empty.");
        }
        if (goldCount < 0) {
            throw new IllegalArgumentException("Gold count cannot be negative.");
        }
        golds.merge(country, goldCount, Integer::sum);
    }

    public int getGoldCount(String country) {
        return golds.getOrDefault(country, 0);
    }

    public List<Map.Entry<String, Integer>> getSortedStandings() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(golds.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        return entries;
    }

    public int countryCount() {
        return golds.size();
    }
}
