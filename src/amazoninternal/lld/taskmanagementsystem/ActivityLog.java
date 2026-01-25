package amazoninternal.lld.taskmanagementsystem;

public class ActivityLog {
    private String msg;
    private long timestamp;

    public ActivityLog(String msg) {
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public String printLog() {
        return "----- Activity log : " + msg + " at time : " + timestamp;
    }
}
