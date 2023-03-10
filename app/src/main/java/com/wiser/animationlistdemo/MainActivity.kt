package com.wiser.animationlistdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wiser.animationlistdemo.loginanim.LoginPageActivity
import com.wiser.animationlistdemo.niftynotification.NotifyActivity
import com.wiser.animationlistdemo.recyclerviewanim.RecyclerViewAnimActivity
import com.wiser.animationlistdemo.tipanim.TipPageActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun skipLoginAnimPage(view: View) {
        startActivity(Intent(this, LoginPageActivity::class.java))
    }
    fun skipListAnimPage(view: View) {
        startActivity(Intent(this, RecyclerViewAnimActivity::class.java))
    }
    fun skipTipAnimPage(view: View) {
        startActivity(Intent(this, TipPageActivity::class.java))
    }

    fun skipNotifyAnimPage(view: View) {
        startActivity(Intent(this, NotifyActivity::class.java))
    }
}