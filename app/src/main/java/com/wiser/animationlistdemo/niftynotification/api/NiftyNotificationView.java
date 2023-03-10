package com.wiser.animationlistdemo.niftynotification.api;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NiftyNotificationView {
    private static final int IMAGE_ID = 16908294;
    private static final String NULL_PARAMETERS_ARE_NOT_ACCEPTED = "Null parameters are not accepted";
    private static final int TEXT_ID = 16908299;
    private Activity activity;
    private Configuration configuration = null;
    private final Effects effects;
    private Drawable iconDrawable;
    private int iconRes;
    private boolean isDefault;
    private FrameLayout notifyView;
    private View.OnClickListener onClickListener;
    private final CharSequence text;
    private ViewGroup viewGroup;

    private NiftyNotificationView(Activity activity2, CharSequence text2, Effects effects2, ViewGroup viewGroup2) {
        if (activity2 == null || text2 == null) {
            throw new IllegalArgumentException(NULL_PARAMETERS_ARE_NOT_ACCEPTED);
        }
        this.isDefault = true;
        this.activity = activity2;
        this.text = text2;
        this.effects = effects2;
        this.viewGroup = viewGroup2;
        this.configuration = new Configuration.Builder().build();
        init(effects2);
    }

    private NiftyNotificationView(Activity activity2, CharSequence text2, Effects effects2, ViewGroup viewGroup2, Configuration configuration2) {
        if (activity2 == null || text2 == null || configuration2 == null) {
            throw new IllegalArgumentException(NULL_PARAMETERS_ARE_NOT_ACCEPTED);
        }
        this.isDefault = false;
        this.activity = activity2;
        this.text = text2;
        this.effects = effects2;
        this.viewGroup = viewGroup2;
        this.configuration = configuration2;
        init(effects2);
    }

    private void init(Effects effects2) {
        this.iconDrawable = null;
        this.iconRes = 0;
    }

    public static NiftyNotificationView build(Activity activity2, CharSequence text2, Effects effects2, int viewGroupResId) {
        return new NiftyNotificationView(activity2, text2, effects2, (ViewGroup) activity2.findViewById(viewGroupResId));
    }

    public static NiftyNotificationView build(Activity activity2, CharSequence text2, Effects effects2, int viewGroupResId, Configuration configuration2) {
        return new NiftyNotificationView(activity2, text2, effects2, (ViewGroup) activity2.findViewById(viewGroupResId), configuration2);
    }

    public long getInDuration() {
        return this.effects.getAnimator().getDuration();
    }

    public long getOutDuration() {
        return this.effects.getAnimator().getDuration();
    }

    public long getDispalyDuration() {
        return this.configuration.dispalyDuration;
    }

    public Effects getEffects() {
        return this.effects;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    /* access modifiers changed from: package-private */
    public Activity getActivity() {
        return this.activity;
    }

    /* access modifiers changed from: package-private */
    public boolean isShowing() {
        return this.activity != null && isNotifyViewNotNull();
    }

    private boolean isNotifyViewNotNull() {
        return (this.notifyView == null || this.notifyView.getParent() == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void detachActivity() {
        this.activity = null;
    }

    /* access modifiers changed from: package-private */
    public void detachViewGroup() {
        this.viewGroup = null;
    }

    /* access modifiers changed from: package-private */
    public ViewGroup getViewGroup() {
        return this.viewGroup;
    }

    /* access modifiers changed from: package-private */
    public View getView() {
        if (this.notifyView == null) {
            initializeNotifyView();
        }
        return this.notifyView;
    }

    private void initializeNotifyView() {
        this.notifyView = initializeCroutonViewGroup();
        this.notifyView.addView(initializeContentView());
    }

    private FrameLayout initializeCroutonViewGroup() {
        FrameLayout notifyView2 = new FrameLayout(this.activity);
        if (this.onClickListener != null) {
            notifyView2.setOnClickListener(this.onClickListener);
        }
        notifyView2.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        return notifyView2;
    }

    private RelativeLayout initializeContentView() {
        RelativeLayout contentView = new RelativeLayout(this.activity);
        contentView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        ImageView image = null;
        if (!(this.iconDrawable == null && this.iconRes == 0)) {
            image = initializeImageView();
            contentView.addView(image, image.getLayoutParams());
        }
        TextView text2 = initializeTextView();
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(-1, -2);
        if (image != null) {
            textParams.addRule(1, image.getId());
        }
        textParams.addRule(13);
        contentView.addView(text2, textParams);
        return contentView;
    }

    private TextView initializeTextView() {
        int padding = px2dip((float) this.configuration.textPadding);
        int viewHeight = px2dip((float) this.configuration.viewHeight);
        TextView text2 = new TextView(this.activity);
        text2.setMaxHeight(viewHeight);
        text2.setMaxHeight(viewHeight);
        text2.setId(TEXT_ID);
        text2.setText(this.text);
        text2.setMaxLines(this.configuration.textLines);
        text2.setEllipsize(TextUtils.TruncateAt.END);
        text2.setPadding(padding * 2, padding, padding * 2, padding);
        text2.setTextColor(Color.parseColor(this.configuration.textColor));
        text2.setBackgroundColor(Color.parseColor(this.configuration.backgroundColor));
        if (this.iconDrawable == null && this.iconRes == 0) {
            text2.setGravity(this.isDefault ? 17 : this.configuration.textGravity);
        } else {
            text2.setMinHeight(viewHeight);
            text2.setGravity(this.isDefault ? 16 : this.configuration.textGravity);
        }
        return text2;
    }

    private ImageView initializeImageView() {
        int maxValue = px2dip((float) this.configuration.viewHeight);
        ImageView image = new ImageView(this.activity);
        image.setMinimumHeight(maxValue);
        image.setMinimumWidth(maxValue);
        image.setMaxWidth(maxValue);
        image.setMaxHeight(maxValue);
        image.setId(IMAGE_ID);
        image.setBackgroundColor(Color.parseColor(this.configuration.iconBackgroundColor));
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (this.iconDrawable != null) {
            image.setImageDrawable(this.iconDrawable);
        }
        if (this.iconRes != 0) {
            image.setImageResource(this.iconRes);
        }
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(-2, -2);
        imageParams.addRule(9, -1);
        imageParams.addRule(15, -1);
        image.setLayoutParams(imageParams);
        return image;
    }

    public int px2dip(float pxValue) {
        return (int) ((pxValue * this.activity.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public NiftyNotificationView setIcon(Drawable iconDrawable2) {
        this.iconDrawable = iconDrawable2;
        return this;
    }

    public NiftyNotificationView setIcon(int iconRes2) {
        this.iconRes = iconRes2;
        return this;
    }

    public NiftyNotificationView setOnClickListener(View.OnClickListener onClickListener2) {
        this.onClickListener = onClickListener2;
        return this;
    }

    public void show() {
        Manager.getInstance().add(this);
    }

    public void hide() {
        Manager.getInstance().removeNotify(this);
    }
}
