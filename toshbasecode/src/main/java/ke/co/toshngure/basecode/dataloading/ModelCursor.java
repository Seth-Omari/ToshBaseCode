/*
 * Copyright (c) 2017.
 *
 * Full Name : Anthony Ngure W.
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.dataloading;

/**
 * Created by Anthony Ngure on 04/06/2017.
 * Email : anthonyngure25@gmail.com.
 * Company : VibeCampo Social Network..
 */

public class ModelCursor {

    private long after;
    private long before;

    public ModelCursor() {
    }

    public ModelCursor(long after, long before) {
        this.after = after;
        this.before = before;
    }

    public void setAfter(long after) {
        this.after = after;
    }

    public void setBefore(long before) {
        this.before = before;
    }

    public long getAfter() {
        return after;
    }

    public long getBefore() {
        return before;
    }
}
