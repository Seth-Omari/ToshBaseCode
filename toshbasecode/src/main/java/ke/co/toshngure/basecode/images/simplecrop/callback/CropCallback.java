/*
 * Copyright (c) 2017.
 *
 * Full Name : Anthony Ngure W.
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.images.simplecrop.callback;

import android.graphics.Bitmap;

public interface CropCallback extends Callback {
    void onSuccess(Bitmap cropped);
    void onError();
}
