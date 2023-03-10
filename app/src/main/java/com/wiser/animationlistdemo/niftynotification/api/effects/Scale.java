package com.wiser.animationlistdemo.niftynotification.api.effects;

import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

public class Scale extends BaseEffect {
    /* access modifiers changed from: protected */
    public void setInAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) ((-view.getHeight()) / 2), 0.0f}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "scaleX", new float[]{0.3f, 0.5f, 1.0f}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.3f, 0.5f, 1.0f, 1.1f, 1.0f}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f}).setDuration((this.mDuration * 3) / 2)});
    }

    /* access modifiers changed from: protected */
    public void setOutAnimation(View view) {
        getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, (float) ((-view.getHeight()) / 2)}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.0f, 0.5f, 0.0f}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.0f, 0.5f, 0.0f}).setDuration(this.mDuration), ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f}).setDuration((this.mDuration * 3) / 2)});
    }

    /* access modifiers changed from: protected */
    public long getAnimDuration(long duration) {
        return duration;
    }
}
