package com.wiser.animationlistdemo.loginanim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.doOnEnd
import com.wiser.animationlistdemo.R

class LoginPageActivity : AppCompatActivity() {

    private val etEmail: AppCompatEditText? by lazy { findViewById(R.id.et_email) }
    private val etPassword: AppCompatEditText? by lazy { findViewById(R.id.et_password) }
    private val ivLeftEye: WinkAnim? by lazy { findViewById(R.id.iv_left_eye) }
    private val ivRightEye: WinkAnim? by lazy { findViewById(R.id.iv_right_eye) }
    private val ivLeftHand: AppCompatImageView? by lazy { findViewById(R.id.iv_hand_left) }
    private val ivRightHand: AppCompatImageView? by lazy { findViewById(R.id.iv_hand_right) }
    private val flIp: ViewGroup? by lazy { findViewById(R.id.fl_ip) }

    private val normalSpace: Float by lazy { resources.getDimension(R.dimen.dimen12) }

    private val dis1 by lazy { resources.getDimension(R.dimen.ksl_dp_70) }
    private val dis2 by lazy { resources.getDimension(R.dimen.ksl_dp_50) }
    private val animDuration = 1000L
    private var isAnimRunning = false
    private var isOpenEye = false
    private var isCoverEye = false

    //旋转 x方向移动 y方向移动
    //左手臂向上旋转
    private val leftArmUpAnim: AnimatorSet by lazy {
        val rotate = ObjectAnimator.ofFloat(ivLeftHand, "rotation", -110f, 0f).apply {
            duration = animDuration
        }
        //手臂向右移动
        val tx = PropertyValuesHolder.ofFloat("translationX", dis1)
        //手臂向上移动
        val ty = PropertyValuesHolder.ofFloat("translationY", -dis2)
        val translate = ObjectAnimator.ofPropertyValuesHolder(ivLeftHand, tx, ty)

        AnimatorSet().apply {
            playTogether(rotate, translate)
            duration = animDuration
        }
    }

    //左手臂向下旋转
    private val leftArmDownAnim: AnimatorSet by lazy {
        val rotate = ObjectAnimator.ofFloat(ivLeftHand, "rotation", -110f).apply {
            duration = animDuration
        }
        //手臂向左移动
        val tx = PropertyValuesHolder.ofFloat("translationX", 0f)
        //手臂向下移动
        val ty = PropertyValuesHolder.ofFloat("translationY", 0f)
        val translate = ObjectAnimator.ofPropertyValuesHolder(ivLeftHand, tx, ty)

        AnimatorSet().apply {
            playTogether(rotate, translate)
            duration = animDuration
        }
    }

    //右手臂向上旋转
    private val rightArmUpAnim: AnimatorSet by lazy {
        val rotate = ObjectAnimator.ofFloat(ivRightHand, "rotation", 0f).apply {
            duration = animDuration
        }
        //手臂向左移动
        val tx = PropertyValuesHolder.ofFloat("translationX", -dis1)
        //手臂向上移动
        val ty = PropertyValuesHolder.ofFloat("translationY", -dis2)
        val translate = ObjectAnimator.ofPropertyValuesHolder(ivRightHand, tx, ty)

        AnimatorSet().apply {
            playTogether(rotate, translate)
            duration = animDuration
        }
    }

    //右手臂向下旋转
    private val rightArmDownAnim: AnimatorSet by lazy {
        val rotate = ObjectAnimator.ofFloat(ivRightHand, "rotation", 110f).apply {
            duration = animDuration
        }
        //手臂向右移动
        val tx = PropertyValuesHolder.ofFloat("translationX", 0f)
        //手臂向下移动
        val ty = PropertyValuesHolder.ofFloat("translationY", 0f)
        val translate = ObjectAnimator.ofPropertyValuesHolder(ivRightHand, tx, ty)

        AnimatorSet().apply {
            playTogether(rotate, translate)
            duration = animDuration
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

//        findViewById<FoldableItemLayout>(R.id.fl_head)?.setFoldRotation(100f)

        etEmail?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
//                setEyeTranslation(etEmail?.paint?.measureText(p0.toString()) ?: 0f)
            }

        })

        etEmail?.setOnFocusChangeListener { view, b ->
            if (b && etEmail?.length() == 0 && ivLeftEye?.translationX == 0f) {
//                val valueAnim = ValueAnimator.ofFloat(0f, -normalSpace)
//                valueAnim.addUpdateListener {
//                    val value: Float = it.animatedValue as Float
//                    ivLeftEye?.translationX = value
//                    ivLeftEye?.rotation = value
//                    ivRightEye?.translationX = value
//                    ivRightEye?.rotation = value
//                    flIp?.rotationY = value
//                }
//                valueAnim.duration = 400
//                valueAnim.start()
            }
        }

        etPassword?.setOnFocusChangeListener { view, b ->
            if (b) {
                coverEye()
            } else {
                openEye()
            }
        }

//        etEmail?.setOnFocusChangeListener { view, b ->
//            if (b && etEmail?.length() == 0 && ivLeftEye?.translationX == 0f) {
//                val centerX = (flIp?.width?:0) / 2
//                val centerY = (flIp?.height?:0) / 2
//                val camera = Camera()
//                val matrix: Matrix = Matrix()
//                val valueAnim = ValueAnimator.ofFloat(0f,45f)
//                valueAnim.addUpdateListener {
//                    camera.save()
//                    val angle: Float = it.animatedValue as Float
//                    println("===========angle=>>$angle")
//                    camera.rotateX(angle)
//                    //把我们的摄像头加在变换矩阵上
//                    camera.getMatrix(matrix)
//                    //设置翻转中心点
//                    matrix.preTranslate(-centerX.toFloat(), -centerY.toFloat())
//                    matrix.postTranslate(centerX.toFloat(), centerY.toFloat())
//                    camera.restore()
//                }
//                valueAnim.duration = 400
//                valueAnim.start()
//            }
//        }

//        val animator = ObjectAnimator.ofFloat(flIp,"translationY",-50f,50f)
//        animator?.duration = 1800
//        animator?.repeatCount = ObjectAnimator.INFINITE
//        animator?.repeatMode = ObjectAnimator.REVERSE
//        animator?.start()

    }

    // 眼睛移动距离
    private var x = 0f

    /**
     * 设置眼睛移动
     */
    private fun setEyeTranslation(distance: Float) {
        println("============>$distance")
        val width: Float =
            (etEmail?.width ?: 0).toFloat() - 2 * (etEmail?.paddingLeft ?: 0).toFloat()
        var d = distance
        if (d > width) {
            d = width
        }
        x = ((normalSpace / (width / 2)) * d - normalSpace)
        //越界处理
        if (x < -normalSpace) {
            x = -normalSpace
        }

        ivLeftEye?.translationX = x
        ivLeftEye?.rotation = x
        ivRightEye?.translationX = x
        ivRightEye?.rotation = x
//        flIp?.rotationY = x
    }

    /**
    遮住眼睛
    两只手掌同时向下移动  ->两只手同时旋转
    AnimatorSet
    实现创建一次有两种方法
    懒加载
    定义一个变量  定义一个方法  在方法中判断这个变量是否有值
     */
    private fun coverEye() {
        isCoverEye = true
        if (isAnimRunning) {
            return
        }
        isAnimRunning = true
        AnimatorSet().apply {
            play(leftArmUpAnim)
                .with(rightArmUpAnim)
            start()
            doOnEnd {
                isAnimRunning = false
                isCoverEye = false
                if (isOpenEye) {
                    openEye()
                }
            }
        }
    }

    /**
    打开眼睛
    两只手同时旋转  ->两个手掌同时向上移动
     */
    private fun openEye() {
        isOpenEye = true
        if (isAnimRunning) {
            return
        }
        isAnimRunning = true
        AnimatorSet().apply {
            play(leftArmDownAnim)
                .with(rightArmDownAnim)
            start()
            doOnEnd {
                isAnimRunning = false
                isOpenEye = false
                if (isCoverEye) {
                    coverEye()
                }
            }
        }
    }
}