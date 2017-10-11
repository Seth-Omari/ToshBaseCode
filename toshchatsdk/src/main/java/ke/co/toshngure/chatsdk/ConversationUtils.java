/*
 * Copyright (c) 2017.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.chatsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;


/**
 * Created by Anthony Ngure on 01/02/2017.
 * Email : anthonyngure25@gmail.com.
 * Company : VibeCampo Social Network..
 */

public class ConversationUtils {

    private static final String TAG = "ConversationUtils";
    public static final String EXTRA_USER = "extra_user";
    public static final String ACTION_USER_CONNECTED_STATUS_CHANGE = "ke.co.toshngure.chatsdk.ACTION_USER_CONNECTED_STATUS_CHANGE";
    public static final String ACTION_USER_TYPING = "ke.co.toshngure.chatsdk.ACTION_USER_TYPING";
    public static final String ACTION_USER_PRESENT = "ke.co.toshngure.chatsdk.ACTION_USER_PRESENT";

    public static void onUserConnected(Context context){
        Intent intent = new Intent(ACTION_USER_CONNECTED_STATUS_CHANGE);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void onUserTyping(Context context){
        Intent intent = new Intent(ACTION_USER_TYPING);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void registerForUserTyping(BroadcastReceiver receiver, Context context){
        IntentFilter filter = new IntentFilter(ACTION_USER_TYPING);
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(receiver, filter);
    }

    public static void registerForUserConnectedStatus(BroadcastReceiver receiver, Context context){
        IntentFilter filter = new IntentFilter(ACTION_USER_CONNECTED_STATUS_CHANGE);
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }

    public static void onUserPresent(Context context) {
        Intent intent = new Intent(ACTION_USER_PRESENT);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void registerForUserPresence(BroadcastReceiver receiver, Context context) {
        IntentFilter filter = new IntentFilter(ACTION_USER_PRESENT);
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(receiver, filter);
    }
}
