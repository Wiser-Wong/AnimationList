package com.wiser.animationlistdemo.niftynotification.api.effects;

import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

public class SlideIn extends BaseEffect {
    /* access modifiers changed from: protected */
    public void setInAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "translationX", new float[]{(float) (-view.getWidth()), -10.0f, -20.0f, -5.0f, -10.0f, 0.0f}).setDuration(this.mDuration)});
    }

    /* access modifiers changed from: protected */
    public void setOutAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, -10.0f, (float) view.getWidth()}).setDuration(this.mDuration)});
    }

    /* access modifiers changed from: protected */
    public long getAnimDuration(long duration) {
        return duration;
    }
}
