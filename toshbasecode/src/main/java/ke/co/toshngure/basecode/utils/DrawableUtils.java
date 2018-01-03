/*
 * Copyright (c) 2018.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Anthony Ngure on 01/02/2017.
 * Email : anthonyngure25@gmail.com.
 *
 */

public class DrawableUtils {


    public static Drawable tintDrawable(Context context, Drawable drawable, @ColorRes int color) {
        if (context == null)
            throw new NullPointerException("instance of context can not be null");
        if (drawable == null)
            throw new NullPointerException("instance of drawable can not be null");
        Drawable wrap = DrawableCompat.wrap(drawable);
        wrap = wrap.mutate();
        DrawableCompat.setTint(wrap, ContextCompat.getColor(context, color));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_IN);
        return wrap;
    }

}
