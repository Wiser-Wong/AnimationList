package com.wiser.animationlistdemo.niftynotification.api.effects;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.ViewHelper;
import com.wiser.animationlistdemo.niftynotification.api.Configuration;

public abstract class BaseEffect {
    public static final int DURATION = Configuration.ANIM_DURATION;
    private AnimatorSet mAnimatorSet = new AnimatorSet();

    public long mDuration = ((long) DURATION);

    /* access modifiers changed from: protected */
    public abstract long getAnimDuration(long j);

    /* access modifiers changed from: protected */
    public abstract void setInAnimation(View view);

    /* access modifiers changed from: protected */
    public abstract void setOutAnimation(View view);

    public void in(View view) {
        reset(view);
        setInAnimation(view);
        this.mAnimatorSet.start();
    }

    public void out(View view) {
        reset(view);
        setOutAnimation(view);
        this.mAnimatorSet.start();
    }

    public void reset(View view) {
        ViewHelper.setPivotX(view, ((float) view.getWidth()) / 2.0f);
        ViewHelper.setPivotY(view, ((float) view.getHeight()) / 2.0f);
    }

    public BaseEffect setDuration(long duration) {
        this.mDuration = duration;
        return this;
    }

    public long getDuration() {
        return getAnimDuration(this.mDuration);
    }

    public AnimatorSet getAnimatorSet() {
        return this.mAnimatorSet;
    }
}
