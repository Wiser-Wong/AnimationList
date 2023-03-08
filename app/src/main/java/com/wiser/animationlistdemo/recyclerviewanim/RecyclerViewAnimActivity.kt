package com.wiser.animationlistdemo.recyclerviewanim

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wiser.animationlistdemo.R
import com.wiser.recycerviewanimdemo.data.AnimBean

class RecyclerViewAnimActivity : AppCompatActivity() {

    private val rlv: RecyclerView? by lazy { findViewById(R.id.rlv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_anim_page)

        rlv?.layoutManager = LinearLayoutManager(this)
        rlv?.adapter = MainAdapter(getData())
        rlv?.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    RotateAnimationZ.angle = 45
                } else {
                    RotateAnimationZ.angle = -45
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager is LinearLayoutManager) {
                    //获取最后一个可见view的位置
                    val lastItemPosition = layoutManager.findLastVisibleItemPosition()
                    //获取第一个可见view的位置
                    val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
                    for (i in firstItemPosition..lastItemPosition) {
                        val view = layoutManager.findViewByPosition(i)
                    }
                }
            }
        })
    }

    private fun getData(): MutableList<AnimBean>? {
        val list = mutableListOf<AnimBean>()
        for (i in 0..50) {
            val animBean = AnimBean()
            if (i % 2 == 1) {
                animBean.ipHeader = R.drawable.tuzi
                animBean.background = R.drawable.shape_66cdaa_radius
                animBean.backgroundColor = Color.parseColor("#66CDAA")
                animBean.title = "Rabbit"
            } else if (i / 2 == 1) {
                animBean.ipHeader = R.drawable.hippo
                animBean.background = R.drawable.shape_yellow_radius
                animBean.backgroundColor = Color.parseColor("#FFC125")
                animBean.title = "Hippo"
            } else if (i / 3 == 1) {
                animBean.ipHeader = R.drawable.tiger
                animBean.background = R.drawable.shape_ff8247_radius
                animBean.backgroundColor = Color.parseColor("#FF8247")
                animBean.title = "Tiger"
            } else {
                animBean.ipHeader = R.drawable.lion
                animBean.background = R.drawable.shape_8b6969_radius
                animBean.backgroundColor = Color.parseColor("#8B6969")
                animBean.title = "Lion"
            }
            list.add(animBean)
        }
        return list
    }
}

class MainAdapter(private val list: MutableList<AnimBean>?) : RecyclerView.Adapter<MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_recyclerview_anim_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val animBean = list?.get(position)
        animBean?.apply {
            holder.ipHeader?.setImageResource(ipHeader)
            holder.backgroundView?.setBackgroundResource(background)
            holder.title?.text = title ?: ""

            holder.itemView.setOnClickListener {
                val i = Intent(holder.itemView.context, DetailsActivity::class.java)
                i.putExtra("background", background)
                i.putExtra("ipHeader", ipHeader)
                i.putExtra("title", title)
                val pairIpHeader: androidx.core.util.Pair<View, String> =
                    androidx.core.util.Pair(holder.ipHeader, "ipHeader")
                val pairBackground: androidx.core.util.Pair<View, String> =
                    androidx.core.util.Pair(holder.backgroundView, "background")
                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        holder.itemView.context as AppCompatActivity,
                        pairBackground, pairIpHeader
                    )
                startActivity(holder.itemView.context, i, optionsCompat.toBundle())
            }
        }
    }

    override fun onViewAttachedToWindow(holder: MainHolder) {
        super.onViewAttachedToWindow(holder)
//        holder.itemView.clearAnimation()
        val animZ = RotateAnimationZ()
        animZ.duration = 800
        animZ.isZhengfangxiang = true
        animZ.interpolator = AccelerateDecelerateInterpolator()
        holder.itemView.startAnimation(animZ)
    }
}

class MainHolder(itemView: View) : ViewHolder(itemView) {
    val backgroundView: FrameLayout? = itemView.findViewById(R.id.fl_background)
    val ipHeader: AppCompatImageView? = itemView.findViewById(R.id.iv_ip)
    val title: AppCompatTextView? = itemView.findViewById(R.id.tv_title)
}