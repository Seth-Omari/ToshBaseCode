/*
 * Copyright (c) 2018.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.demo.basecode.model;

/**
 * Created by Anthony Ngure on 03/01/2018.
 * Email : anthonyngure25@gmail.com.
 */

public class Post {

    private long Id;
    private long UserId;
    private String Title;
    private String Body;

    public Post() {
    }

    public String getTitle() {
        return Title;
    }

    public String getBody() {
        return Body;
    }

    public long getId() {
        return Id;
    }

    public long getUserId() {
        return UserId;
    }
}
