package com.wiser.animationlistdemo.niftynotification.api;

public class Configuration {
    public static final int ANIM_DISPLAY_DURATION = 1500;
    public static final int ANIM_DURATION = 700;
    final long animDuration;
    final String backgroundColor;
    final long dispalyDuration;
    final String iconBackgroundColor;
    final String textColor;
    final int textGravity;
    final int textLines;
    final int textPadding;
    final int viewHeight;

    private Configuration(Builder builder) {
        this.animDuration = builder.animDuration;
        this.textColor = builder.textColor;
        this.dispalyDuration = builder.dispalyDuration;
        this.backgroundColor = builder.backgroundColor;
        this.textPadding = builder.textPadding;
        this.viewHeight = builder.viewHeight;
        this.iconBackgroundColor = builder.iconBackgroundColor;
        this.textGravity = builder.textGravity;
        this.textLines = builder.textLines;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public long animDuration;
        /* access modifiers changed from: private */
        public String backgroundColor;
        /* access modifiers changed from: private */
        public long dispalyDuration;
        /* access modifiers changed from: private */
        public String iconBackgroundColor;
        /* access modifiers changed from: private */
        public String textColor;
        /* access modifiers changed from: private */
        public int textGravity;
        /* access modifiers changed from: private */
        public int textLines;
        /* access modifiers changed from: private */
        public int textPadding;
        /* access modifiers changed from: private */
        public int viewHeight;

        public Builder() {
            this.animDuration = 700;
            this.dispalyDuration = 1500;
            this.textColor = "#FF444444";
            this.backgroundColor = "#FFBDC3C7";
            this.textPadding = 5;
            this.viewHeight = 48;
            this.iconBackgroundColor = "#FFFFFFFF";
            this.textGravity = 17;
            this.textLines = 2;
        }

        public Builder(Configuration baseStyle) {
            this.animDuration = baseStyle.animDuration;
            this.textColor = baseStyle.textColor;
            this.backgroundColor = baseStyle.backgroundColor;
            this.textPadding = baseStyle.textPadding;
            this.viewHeight = baseStyle.viewHeight;
            this.iconBackgroundColor = baseStyle.iconBackgroundColor;
            this.textGravity = baseStyle.textGravity;
            this.textLines = baseStyle.textLines;
        }

        public Builder setAnimDuration(long animDuration2) {
            this.animDuration = animDuration2;
            return this;
        }

        public Builder setDispalyDuration(long dispalyDuration2) {
            this.dispalyDuration = dispalyDuration2;
            return this;
        }

        public Builder setTextColor(String textColor2) {
            this.textColor = textColor2;
            return this;
        }

        public Builder setBackgroundColor(String backgroundColor2) {
            this.backgroundColor = backgroundColor2;
            return this;
        }

        public Builder setTextPadding(int textPadding2) {
            this.textPadding = textPadding2;
            return this;
        }

        public Builder setViewHeight(int viewHeight2) {
            this.viewHeight = viewHeight2;
            return this;
        }

        public Builder setIconBackgroundColor(String iconBackgroundColor2) {
            this.iconBackgroundColor = iconBackgroundColor2;
            return this;
        }

        public Builder setTextGravity(int textGravity2) {
            this.textGravity = textGravity2;
            return this;
        }

        public Builder setTextLines(int textLines2) {
            this.textLines = textLines2;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
