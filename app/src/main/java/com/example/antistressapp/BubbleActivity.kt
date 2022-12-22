package com.example.antistressapp

import android.animation.Animator
import android.app.Activity
import android.os.Bundle
import com.example.antistressapp.R
import androidx.constraintlayout.widget.ConstraintLayout
import android.graphics.drawable.AnimationDrawable
import com.example.antistressapp.BubbleActivity
import android.view.animation.AlphaAnimation
import android.view.animation.LinearInterpolator
import android.view.animation.Animation
import android.widget.RelativeLayout
import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Handler
import android.widget.ImageView
import java.util.*

class BubbleActivity : Activity() {
    var handler = Handler()
    var runnable: Runnable? = null
    var delay = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble)
        val layout: ConstraintLayout = findViewById(R.id.bubbleLayout)
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()
        val display = windowManager.defaultDisplay
        val screenSize = Point()
        display.getSize(screenSize)
        val context = this
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())
            val bubble = ImageView(context)
            bubble.setImageDrawable(resources.getDrawable(R.drawable.bubble))
            bubble.x = randomFloat(0f, (screenSize.x - 350).toFloat())
            bubble.y = 0f
            bubble.setOnClickListener { view ->

                var alphaAnimator = ObjectAnimator.ofFloat(bubble, "alpha", 1f, 0f)
                alphaAnimator.duration= 700
                alphaAnimator.interpolator = LinearInterpolator()
                alphaAnimator.start()


                alphaAnimator.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator) {}
                    override fun onAnimationEnd(animator: Animator) {
                        layout.removeView(bubble)
                    }

                    override fun onAnimationCancel(animator: Animator) {}
                    override fun onAnimationRepeat(animator: Animator) {}
                })
//                view.animation = alphaAnimation
            }
            val layoutParams = RelativeLayout.LayoutParams(350, 350)
            layout.addView(bubble, layoutParams)
            val animator =
                ObjectAnimator.ofFloat(bubble, "translationY", screenSize.y.toFloat(), -350f)
            animator.duration = 8000
            animator.interpolator = LinearInterpolator()
            animator.start()
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    layout.removeView(bubble)
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
        }.also { runnable = it }, delay.toLong())
    }

    private fun randomFloat(min: Float, max: Float): Float {
        val rand = Random()
        return rand.nextFloat() * (max - min) + min
    }
}