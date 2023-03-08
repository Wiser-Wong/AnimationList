package com.wiser.animationlistdemo.tipanim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.animation.doOnEnd
import com.wiser.animationlistdemo.R

class TipPageActivity : AppCompatActivity() {

    private val tvTip: AppCompatTextView? by lazy { findViewById(R.id.tv_tip) }
    private val tvToggle: AppCompatTextView? by lazy { findViewById(R.id.tv_toggle) }

    private val alphaAnimatorShow: ObjectAnimator? by lazy { ObjectAnimator.ofFloat(tvTip,"alpha", 0f, 1f) }
    private val translationAnimatorShow: ObjectAnimator? by lazy { ObjectAnimator.ofFloat(tvTip,"translationX", tvToggle?.width?.toFloat()!!,
        -tvToggle?.width?.toFloat()!! - 30f
    ) }
    private val alphaAnimatorHide: ObjectAnimator? by lazy { ObjectAnimator.ofFloat(tvTip,"alpha", 1f, 0f) }
    private val translationAnimatorHide: ObjectAnimator? by lazy { ObjectAnimator.ofFloat(tvTip,"translationX", -tvToggle?.width?.toFloat()!! - 30f,
        tvToggle?.width?.toFloat()!!
    ) }

    private var isOpen = false
    private var isRunningAnim = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_page)

        tvToggle?.setOnClickListener {
            if (isOpen) {
                isOpen = false
                hideAnim()
            } else {
                isOpen = true
                showAnim()
            }
        }
    }

    private fun showAnim() {
        if (isRunningAnim) return
        isRunningAnim = true
        AnimatorSet().apply {
            tvTip?.visibility = View.VISIBLE
            playTogether(alphaAnimatorShow, translationAnimatorShow)
            duration = 1000
            start()
            doOnEnd {
                AnimatorSet().apply {
                    playTogether(alphaAnimatorHide, translationAnimatorHide)
                    duration = 1000
                    startDelay = 3000
                    start()
                    doOnEnd {
                        isRunningAnim = false
                    }
                }
            }
        }
    }

    private fun hideAnim() {
        if (isRunningAnim) return
        isRunningAnim = true
        AnimatorSet().apply {
            playTogether(alphaAnimatorHide, translationAnimatorHide)
            duration = 1000
            start()
            doOnEnd {
                tvTip?.visibility = View.INVISIBLE
                isRunningAnim = false
            }
        }
    }
}