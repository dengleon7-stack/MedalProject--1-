import java.util.List;
import java.util.Map;

/**
 * Tests for MedalStandings only.
 * Run with: java MedalStandingsTest
 */
public class MedalStandingsTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testSortedDescending();
        testMergeSameCountry();
        testRejectsEmptyName();
        testRejectsNegativeGold();
        testGoldCountForUnknownCountryIsZero();

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testSortedDescending() {
        MedalStandings ms = new MedalStandings();
        ms.addCountry("USA", 12);
        ms.addCountry("China", 10);
        ms.addCountry("Kenya", 8);
        List<Map.Entry<String, Integer>> sorted = ms.getSortedStandings();
        check("getSortedStandings orders countries by gold count, highest first",
                sorted.get(0).getKey().equals("USA")
                        && sorted.get(1).getKey().equals("China")
                        && sorted.get(2).getKey().equals("Kenya"));
    }

    private static void testMergeSameCountry() {
        MedalStandings ms = new MedalStandings();
        ms.addCountry("USA", 5);
        ms.addCountry("USA", 7);
        check("addCountry adds golds when the same country appears again",
                ms.getGoldCount("USA") == 12 && ms.countryCount() == 1);
    }

    private static void testRejectsEmptyName() {
        MedalStandings ms = new MedalStandings();
        boolean threw = false;
        try {
            ms.addCountry("  ", 5);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check("addCountry rejects an empty country name", threw);
    }

    private static void testRejectsNegativeGold() {
        MedalStandings ms = new MedalStandings();
        boolean threw = false;
        try {
            ms.addCountry("USA", -1);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check("addCountry rejects a negative gold count", threw);
    }

    private static void testGoldCountForUnknownCountryIsZero() {
        MedalStandings ms = new MedalStandings();
        check("getGoldCount returns 0 for a country never added", ms.getGoldCount("Brazil") == 0);
    }

    private static void check(String description, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS - " + description);
        } else {
            failed++;
            System.out.println("FAIL - " + description);
        }
    }
}
