package com.wiser.animationlistdemo.niftynotification.api.effects;

import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

public class Standard extends BaseEffect {
    /* access modifiers changed from: protected */
    public void setInAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) (-view.getHeight()), 0.0f}).setDuration(this.mDuration)});
    }

    /* access modifiers changed from: protected */
    public void setOutAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, (float) (-view.getHeight())}).setDuration(this.mDuration)});
    }

    /* access modifiers changed from: protected */
    public long getAnimDuration(long duration) {
        return duration;
    }
}
