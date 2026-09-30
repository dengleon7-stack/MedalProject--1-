import java.util.List;

/**
 * Tests for ResultManager only.
 * Run with: java ResultManagerTest
 */
public class ResultManagerTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testAddValidResult();
        testAddResultBlankAthleteFails();
        testAddResultInvalidEventFails();
        testAddResultBlankResultFails();
        testAddResultTrimsWhitespace();
        testGetResultsForEvent();
        testClear();

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testAddValidResult() {
        ResultManager rm = new ResultManager();
        ResultRecord r = rm.addResult("Jane Doe", "100m", "11.2s");
        check("addResult stores a valid record",
                rm.getResultCount() == 1
                        && r.getAthlete().equals("Jane Doe")
                        && r.getEvent().equals("100m")
                        && r.getResult().equals("11.2s"));
    }

    private static void testAddResultBlankAthleteFails() {
        ResultManager rm = new ResultManager();
        boolean threw = false;
        try {
            rm.addResult("   ", "100m", "11.2s");
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check("addResult rejects blank athlete name", threw && rm.getResultCount() == 0);
    }

    private static void testAddResultInvalidEventFails() {
        ResultManager rm = new ResultManager();
        boolean threw = false;
        try {
            rm.addResult("Jane Doe", "Marathon", "11.2s");
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check("addResult rejects an event not in the dropdown list", threw && rm.getResultCount() == 0);
    }

    private static void testAddResultBlankResultFails() {
        ResultManager rm = new ResultManager();
        boolean threw = false;
        try {
            rm.addResult("Jane Doe", "200m", "  ");
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check("addResult rejects blank result value", threw && rm.getResultCount() == 0);
    }

    private static void testAddResultTrimsWhitespace() {
        ResultManager rm = new ResultManager();
        ResultRecord r = rm.addResult("  Jane Doe  ", "Relay", "  42.0s  ");
        check("addResult trims surrounding whitespace",
                r.getAthlete().equals("Jane Doe") && r.getResult().equals("42.0s"));
    }

    private static void testGetResultsForEvent() {
        ResultManager rm = new ResultManager();
        rm.addResult("Jane Doe", "100m", "11.2s");
        rm.addResult("John Roe", "200m", "22.1s");
        rm.addResult("Amy Fox", "100m", "11.5s");
        List<ResultRecord> hundredM = rm.getResultsForEvent("100m");
        check("getResultsForEvent filters by event", hundredM.size() == 2);
    }

    private static void testClear() {
        ResultManager rm = new ResultManager();
        rm.addResult("Jane Doe", "100m", "11.2s");
        rm.clear();
        check("clear empties the result list", rm.getResultCount() == 0);
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