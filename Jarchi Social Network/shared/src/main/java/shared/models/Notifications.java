package shared.models;

import java.util.HashMap;
import java.util.LinkedList;

public class Notifications {
    //userid
    LinkedList<Integer> RequestedToFollowMe;
    //userid , state
    HashMap<Integer , Integer> RequestedToFollowThem;
    //userid , followOrUnfollow
    HashMap<Integer , Boolean> FollowOrUnfollow;

    public Notifications() {
        this.RequestedToFollowMe = new LinkedList<>();
        this.RequestedToFollowThem = new HashMap<>();
        this.FollowOrUnfollow = new HashMap<>();
    }

    public LinkedList<Integer> getRequestedToFollowMe() {
        return RequestedToFollowMe;
    }

    public HashMap<Integer, Integer> getRequestedToFollowThem() {
        return RequestedToFollowThem;
    }

    public HashMap<Integer, Boolean> getFollowOrUnfollow() {
        return FollowOrUnfollow;
    }
}
