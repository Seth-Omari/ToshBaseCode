/*
 * Copyright (c) 2017.
 *
 * Full Name : Anthony Ngure W.
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.log;

import android.support.annotation.Nullable;
import android.util.Log;


/**
 * It is used to provide log history in order to show in the bee.
 */
public final class BeeLog {

    private static String TAG = "ToshNgure";
    public static boolean DEBUG = true;

    private BeeLog() {
        // no instance
    }

    public static void init(boolean debug, @Nullable String tag){
        if (tag != null){
            TAG = tag;
        }
        DEBUG = debug;
    }

    @SuppressWarnings("unused")
    public static void d(String tag, String message) {
        if (DEBUG){
            Log.d(TAG, tag + " : " + message);
            addToHistory(tag, message);
        }
    }

    @SuppressWarnings("unused")
    public static void e(String tag, String message) {
       if (DEBUG){
           Log.e(TAG, tag + " : " + message);
           addToHistory(tag, message);
       }
    }

    public static void e(String tag, Exception e) {
        if (DEBUG){
            if (e != null){
                Log.e(TAG, tag + " : " + e.getLocalizedMessage());
                addToHistory(tag, e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unused")
    public static void w(String tag, String message) {
        if (DEBUG){
            Log.w(TAG, tag + " : " + message);
            addToHistory(tag, message);
        }
    }

    @SuppressWarnings("unused")
    public static void i(String tag, String message) {
        if (DEBUG){
            Log.i(TAG, tag + " : " + message);
            addToHistory(tag, message);
        }
    }

    private static void addToHistory(String tag, String message) {
        LogHistoryManager.getInstance().add(new LogItem(tag, message));
    }

    public static void e(String tag, Throwable e) {
        if (DEBUG){
            Log.e(TAG, tag + " : " + e.getMessage());
            addToHistory(tag, e.getMessage());
        }
    }
}
