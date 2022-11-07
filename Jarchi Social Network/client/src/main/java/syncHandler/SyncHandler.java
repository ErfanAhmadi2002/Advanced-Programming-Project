package syncHandler;

public class SyncHandler {
    public final Object REQUEST_SYNC;
    public final Object PROFILE_SYNC;
    public final Object GROUP_SYNC;

    public SyncHandler() {
        REQUEST_SYNC = new Object();
        PROFILE_SYNC = new Object();
        GROUP_SYNC = new Object();
    }
}
