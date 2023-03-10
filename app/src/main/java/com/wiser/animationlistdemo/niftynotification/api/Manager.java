package com.wiser.animationlistdemo.niftynotification.api;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Manager extends Handler {
    private static Manager INSTANCE;
    private final Queue<NiftyNotificationView> notifyQueue = new LinkedBlockingQueue();

    private static final class Messages {
        public static final int ADD_TO_VIEW = 538183700;
        public static final int DISPLAY_NOTIFICATION = 538183699;
        public static final int REMOVE_NOTIFICATION = 538183701;
        public static final int REMOVE_NOTIFICATION_VIEW = 538183702;

        private Messages() {
        }
    }

    private Manager() {
    }

    public static synchronized Manager getInstance() {
        Manager manager;
        synchronized (Manager.class) {
            if (INSTANCE == null) {
                INSTANCE = new Manager();
            }
            manager = INSTANCE;
        }
        return manager;
    }

    /* access modifiers changed from: package-private */
    public void add(NiftyNotificationView crouton) {
        if (this.notifyQueue.size() < 1) {
            this.notifyQueue.add(crouton);
            displayNotify();
        }
    }

    private long calculateCroutonDuration(NiftyNotificationView notify) {
        return notify.getDispalyDuration() + notify.getEffects().getAnimator().getDuration();
    }

    private void sendMessage(NiftyNotificationView crouton, int messageId) {
        Message message = obtainMessage(messageId);
        message.obj = crouton;
        sendMessage(message);
    }

    /* access modifiers changed from: private */
    public void sendMessageDelayed(NiftyNotificationView crouton, int messageId, long delay) {
        Message message = obtainMessage(messageId);
        message.obj = crouton;
        sendMessageDelayed(message, delay);
    }

    public void handleMessage(Message msg) {
        NiftyNotificationView notify = (NiftyNotificationView) msg.obj;
        if (notify != null) {
            switch (msg.what) {
                case Messages.DISPLAY_NOTIFICATION /*538183699*/:
                    displayNotify();
                    break;
                case Messages.ADD_TO_VIEW /*538183700*/:
                    addNotifyToView(notify);
                    break;
                case Messages.REMOVE_NOTIFICATION /*538183701*/:
                    removeNotify(notify);
                    break;
                case Messages.REMOVE_NOTIFICATION_VIEW /*538183702*/:
                    removeNotifyView(notify);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private void displayNotify() {
        if (!this.notifyQueue.isEmpty()) {
            NiftyNotificationView currentNotify = this.notifyQueue.peek();
            if (currentNotify.getActivity() == null) {
                this.notifyQueue.poll();
            }
            if (!currentNotify.isShowing()) {
                sendMessage(currentNotify, Messages.ADD_TO_VIEW);
            } else {
                sendMessageDelayed(currentNotify, Messages.DISPLAY_NOTIFICATION, calculateCroutonDuration(currentNotify));
            }
        }
    }

    private void addNotifyToView(final NiftyNotificationView notify) {
        if (!notify.isShowing()) {
            final View notifyView = notify.getView();
            if (notifyView.getParent() == null) {
                ViewGroup.LayoutParams params = notifyView.getLayoutParams();
                if (params == null) {
                    params = new ViewGroup.MarginLayoutParams(-1, -2);
                }
                if (notify.getViewGroup() == null) {
                    Activity activity = notify.getActivity();
                    if (activity != null && !activity.isFinishing()) {
                        activity.addContentView(notifyView, params);
                    } else {
                        return;
                    }
                } else if (notify.getViewGroup() instanceof FrameLayout) {
                    notify.getViewGroup().addView(notifyView, params);
                } else {
                    notify.getViewGroup().addView(notifyView, 0, params);
                }
            }
            notifyView.requestLayout();
            ViewTreeObserver observer = notifyView.getViewTreeObserver();
            if (observer != null) {
                observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(16)
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT < 16) {
                            notifyView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            notifyView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                        notify.getEffects().getAnimator().setDuration(notify.getConfiguration().animDuration).in(notify.getView());
                        Manager.this.sendMessageDelayed(notify, Messages.REMOVE_NOTIFICATION, notify.getDispalyDuration() + notify.getInDuration());
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void removeNotify(NiftyNotificationView notify) {
        if (((ViewGroup) notify.getView().getParent()) != null) {
            notify.getEffects().getAnimator().setDuration(notify.getConfiguration().animDuration).out(notify.getView());
            sendMessageDelayed(notify, Messages.REMOVE_NOTIFICATION_VIEW, notify.getOutDuration());
            sendMessageDelayed(notify, Messages.DISPLAY_NOTIFICATION, notify.getOutDuration());
        }
    }

    /* access modifiers changed from: protected */
    public void removeNotifyView(NiftyNotificationView notify) {
        View notifyView = notify.getView();
        ViewGroup notifyParentView = (ViewGroup) notifyView.getParent();
        if (notifyParentView != null) {
            NiftyNotificationView removed = this.notifyQueue.poll();
            notifyParentView.removeView(notifyView);
            if (removed != null) {
                removed.detachActivity();
                removed.detachViewGroup();
            }
        }
    }
}
