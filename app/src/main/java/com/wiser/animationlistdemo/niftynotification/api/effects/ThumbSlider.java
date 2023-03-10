package com.wiser.animationlistdemo.niftynotification.api.effects;

import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class ThumbSlider extends BaseEffect {
    long e = ((this.mDuration - 200) / 2);
    View iconView;
    long m = 200;
    View msgView;
    long s = ((this.mDuration - 200) / 2);

    /* access modifiers changed from: protected */
    public void setInAnimation(View view) {
        this.iconView = view.findViewById(16908294);
        if (this.iconView != null) {
            this.msgView = view.findViewById(16908299);
            ViewHelper.setAlpha(this.msgView, 0.0f);
            ViewHelper.setPivotX(this.msgView, 0.0f);
            ViewHelper.setPivotY(this.msgView, 0.0f);
            ObjectAnimator msgScaleAnim = ObjectAnimator.ofFloat(this.msgView, "scaleX", new float[]{0.0f, 0.5f, 1.0f, 1.1f, 1.0f}).setDuration(this.s * 2);
            ObjectAnimator msgAlphaAnim = ObjectAnimator.ofFloat(this.msgView, "alpha", new float[]{1.0f}).setDuration(this.s * 2);
            msgScaleAnim.setStartDelay(this.s + this.m);
            msgAlphaAnim.setStartDelay(this.s + this.m);
            getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(this.iconView, "scaleX", new float[]{0.0f, 0.5f, 1.0f, 0.9f, 1.0f, 1.2f, 1.0f}).setDuration(this.s), ObjectAnimator.ofFloat(this.iconView, "scaleY", new float[]{0.0f, 0.5f, 1.0f, 1.2f, 1.0f, 0.9f, 1.0f}).setDuration(this.s), msgScaleAnim, msgAlphaAnim});
        }
    }

    /* access modifiers changed from: protected */
    public void setOutAnimation(View view) {
        this.iconView = view.findViewById(16908294);
        if (this.iconView != null) {
            this.msgView = view.findViewById(16908299);
            ObjectAnimator iconScaleXAnim = ObjectAnimator.ofFloat(this.iconView, "scaleX", new float[]{1.0f, 1.2f, 1.0f, 0.9f, 1.0f, 0.5f, 0.0f}).setDuration(this.e * 2);
            ObjectAnimator iconScaleYAnim = ObjectAnimator.ofFloat(this.iconView, "scaleY", new float[]{1.0f, 0.9f, 1.0f, 1.2f, 1.0f, 0.5f, 0.0f}).setDuration(this.e * 2);
            ObjectAnimator iconAlphaAnim = ObjectAnimator.ofFloat(this.iconView, "alpha", new float[]{1.0f, 0.0f}).setDuration(this.e * 2);
            iconScaleXAnim.setStartDelay(this.e + this.m);
            iconScaleYAnim.setStartDelay(this.e + this.m);
            iconAlphaAnim.setStartDelay(this.e + this.m);
            getAnimatorSet().playTogether(new Animator[]{ObjectAnimator.ofFloat(this.msgView, "scaleX", new float[]{1.0f, 1.1f, 1.0f, 0.5f, 0.0f}).setDuration(this.e), iconScaleXAnim, iconScaleYAnim, iconAlphaAnim});
        }
    }

    /* access modifiers changed from: protected */
    public long getAnimDuration(long duration) {
        return (2 * duration) + 200;
    }
}
