package com.wiser.animationlistdemo.niftynotification.api;

import com.wiser.animationlistdemo.niftynotification.api.effects.BaseEffect;
import com.wiser.animationlistdemo.niftynotification.api.effects.Flip;
import com.wiser.animationlistdemo.niftynotification.api.effects.Jelly;
import com.wiser.animationlistdemo.niftynotification.api.effects.Scale;
import com.wiser.animationlistdemo.niftynotification.api.effects.SlideIn;
import com.wiser.animationlistdemo.niftynotification.api.effects.SlideOnTop;
import com.wiser.animationlistdemo.niftynotification.api.effects.Standard;
import com.wiser.animationlistdemo.niftynotification.api.effects.ThumbSlider;

public enum Effects {
    standard(Standard.class),
    slideOnTop(SlideOnTop.class),
    flip(Flip.class),
    slideIn(SlideIn.class),
    jelly(Jelly.class),
    thumbSlider(ThumbSlider.class),
    scale(Scale.class);
    
    private Class<? extends BaseEffect> effectsClazz;

    private Effects(Class<? extends BaseEffect> mclass) {
        this.effectsClazz = mclass;
    }

    public BaseEffect getAnimator() {
        try {
            return (BaseEffect) this.effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e2) {
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e3) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
