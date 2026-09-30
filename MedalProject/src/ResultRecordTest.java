/**
 * Tests for ResultRecord only.
 * Run with: java ResultRecordTest
 */
public class ResultRecordTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testGetAthlete();
        testGetEvent();
        testGetResult();
        testToString();

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testGetAthlete() {
        ResultRecord r = new ResultRecord("Jane Doe", "100m", "11.2s");
        check("getAthlete returns the athlete passed to the constructor",
                r.getAthlete().equals("Jane Doe"));
    }

    private static void testGetEvent() {
        ResultRecord r = new ResultRecord("Jane Doe", "100m", "11.2s");
        check("getEvent returns the event passed to the constructor",
                r.getEvent().equals("100m"));
    }

    private static void testGetResult() {
        ResultRecord r = new ResultRecord("Jane Doe", "100m", "11.2s");
        check("getResult returns the result passed to the constructor",
                r.getResult().equals("11.2s"));
    }

    private static void testToString() {
        ResultRecord r = new ResultRecord("Jane Doe", "100m", "11.2s");
        check("toString includes athlete, event, and result",
                r.toString().equals("Jane Doe | 100m | 11.2s"));
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