package com.example.antistressapp

import android.app.Activity
import android.os.Bundle
import com.example.antistressapp.R
import androidx.constraintlayout.widget.ConstraintLayout
import android.graphics.drawable.AnimationDrawable
import android.view.animation.RotateAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.ImageView

class SpinnerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        val layout: ConstraintLayout = findViewById(R.id.spinnerLayout)
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()
        val spinnerImg = findViewById<ImageView>(R.id.spinner_img)
        val anim = RotateAnimation(0f, 360f, 309.5f, 354.5f)
        anim.repeatCount = Animation.INFINITE
        anim.interpolator = LinearInterpolator()
        anim.duration = 2000
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {
                anim.duration = animation.duration + 70
                if (anim.duration >= 2500) {
                    spinnerImg.animation = null
                }
            }
        })
        spinnerImg.setOnClickListener {
            if (anim.hasStarted()) {
                if (anim.duration >= 200) {
                    anim.duration = anim.duration / 2
                }
            }
            if (spinnerImg.animation == null) {
                spinnerImg.startAnimation(anim)
            }
        }
    }
}