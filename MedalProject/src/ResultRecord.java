public class ResultRecord {
    private final String athlete;
    private final String event;
    private final String result;

    public ResultRecord(String athlete, String event, String result) {
        this.athlete = athlete;
        this.event = event;
        this.result = result;
    }

    public String getAthlete() { return athlete; }
    public String getEvent() { return event; }
    public String getResult() { return result; }

    @Override
    public String toString() {
        return athlete + " | " + event + " | " + result;
    }
}
