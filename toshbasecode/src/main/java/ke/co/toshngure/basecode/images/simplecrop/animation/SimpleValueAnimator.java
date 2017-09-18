/*
 * Copyright (c) 2017.
 *
 * Full Name : Anthony Ngure W.
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.images.simplecrop.animation;
@SuppressWarnings("unused")
public interface SimpleValueAnimator {
    void startAnimation(long duration);
    void cancelAnimation();
    boolean isAnimationStarted();
    void addAnimatorListener(SimpleValueAnimatorListener animatorListener);
}
