/*
 * Copyright (c) 2017.
 *
 * Full Name : Anthony Ngure W.
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.networking;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Anthony Ngure on 21/01/2017.
 * Email : anthonyngure25@gmail.com.
 * Company : VibeCampo Social Network..
 */

public class JSONArrayResponse {


    private JSONArray data;

    private JSONObject meta;

    public JSONArrayResponse(JSONArray data, JSONObject meta) {
        this.data = data;
        this.meta = meta;
    }

    public JSONArray getData() {
        return data;
    }

    public JSONObject getMeta() {
        return meta;
    }
}
