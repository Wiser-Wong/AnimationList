package com.wiser.animationlistdemo.niftynotification.api.effects;

import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class Flip extends BaseEffect {
    long e = this.mDuration;
    long s = this.mDuration;

    /* access modifiers changed from: protected */
    public void setInAnimation(View view) {
        ViewHelper.setPivotX(view, (float) (view.getWidth() / 2));
        ViewHelper.setPivotY(view, 0.0f);
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "rotationX", new float[]{-90.0f, 0.0f}).setDuration(this.s), ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}).setDuration((this.s * 3) / 2)});
    }

    /* access modifiers changed from: protected */
    public void setOutAnimation(View view) {
        ViewHelper.setPivotX(view, (float) (view.getWidth() / 2));
        ViewHelper.setPivotY(view, 0.0f);
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "rotationX", new float[]{0.0f, -90.0f}).setDuration(this.e), ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f}).setDuration((this.e * 3) / 2)});
    }

    /* access modifiers changed from: protected */
    public long getAnimDuration(long duration) {
        return duration;
    }
}
