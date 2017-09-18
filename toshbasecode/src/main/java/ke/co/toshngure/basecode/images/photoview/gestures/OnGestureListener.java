/*
 * Copyright (c) 2017.
 *
 * Full Name : Anthony Ngure W.
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.images.photoview.gestures;

public interface OnGestureListener {

    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX,
                 float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);

}