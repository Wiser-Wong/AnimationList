package com.wiser.animationlistdemo.recyclerviewanim

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.wiser.animationlistdemo.R

class DetailsActivity : AppCompatActivity() {

    private val ivIp: AppCompatImageView? by lazy { findViewById(R.id.iv_ip) }
    private val flBackground: FrameLayout? by lazy { findViewById(R.id.fl_background) }
    private val tvTitle: AppCompatTextView? by lazy { findViewById(R.id.tv_title) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_anim_details)
        val ipHeader: Int = intent?.getIntExtra("ipHeader", 0) ?: 0
        val background: Int = intent?.getIntExtra("background", 0) ?: 0
        val title: String? = intent?.getStringExtra("title")
        ivIp?.setImageResource(ipHeader)
        flBackground?.setBackgroundResource(background)
        tvTitle?.text = title ?: ""
    }

}