package com.wiser.animationlistdemo.niftynotification.api.effects;

import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

public class Jelly extends BaseEffect {
    /* access modifiers changed from: protected */
    public void setInAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "scaleX", new float[]{0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1.0f}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.3f, 0.5f, 0.8f, 0.9f, 0.8f, 1.0f}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}).setDuration((this.mDuration * 3) / 2)});
    }

    /* access modifiers changed from: protected */
    public void setOutAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f}).setDuration((this.mDuration * 2) / 3)});
    }

    /* access modifiers changed from: protected */
    public long getAnimDuration(long duration) {
        return duration;
    }
}
