package com.example.antistressapp

import androidx.appcompat.app.AppCompatActivity
import com.example.antistressapp.dialog.EvaluateDialog.EvaluateDialogListener
import android.widget.TextView
import android.os.CountDownTimer
import android.os.Bundle
import com.example.antistressapp.R
import androidx.constraintlayout.widget.ConstraintLayout
import android.graphics.drawable.AnimationDrawable
import android.content.Intent
import android.view.View
import com.example.antistressapp.service.MusicService
import android.view.animation.RotateAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.example.antistressapp.SpinnerActivity
import com.example.antistressapp.BubbleActivity
import com.example.antistressapp.dialog.EvaluateDialog
import com.example.antistressapp.data.StressLevel

class MainActivity : AppCompatActivity(), EvaluateDialogListener {
    var recommendationTextView: TextView? = null
    var timerTextView: TextView? = null
    var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = findViewById<ConstraintLayout>(R.id.mainLayout)
        recommendationTextView = findViewById<View>(R.id.recommendationTextView) as TextView
        timerTextView = findViewById<View>(R.id.timerTextView) as TextView
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()
        startService(Intent(this@MainActivity, MusicService::class.java))
        val spinnerImg = findViewById<ImageView>(R.id.spinner_img_navigation)
        val bubbleImg = findViewById<ImageView>(R.id.bubble_img_navigation)
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
            val myIntent =
                Intent(this@MainActivity, SpinnerActivity::class.java) //Optional parameters
            startActivity(myIntent)
        }
        bubbleImg.setOnClickListener {
            val myIntent =
                Intent(this@MainActivity, BubbleActivity::class.java) //Optional parameters
            startActivity(myIntent)
        }
        openDialog()
    }

    private fun openDialog() {
        val dialog = EvaluateDialog()
        dialog.show(supportFragmentManager, "dialog")
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this@MainActivity, MusicService::class.java))
    }

    override fun applyEvaluation(evaluation: Float) {
        timer = object : CountDownTimer(
            (StressLevel.getSecondsFromStressLevel(evaluation) * 1000).toLong(),
            1000
        ) {
            override fun onTick(remaining: Long) {
                timerTextView!!.text = secondsToTimerText((remaining / 1000).toInt())
            }

            override fun onFinish() {}
        }
        (timer as CountDownTimer).start()
        recommendationTextView!!.text = "Chill for:"
    }

    private fun secondsToTimerText(seconds: Int): String {
        val displayMinutes = seconds / 60
        val displaySeconds = seconds - displayMinutes * 60
        val minutesString: String
        val secondsString: String

        minutesString = if (displayMinutes / 10 > 0) {
            Integer.toString(displayMinutes)
        } else {
            "0" + Integer.toString(displayMinutes)
        }
        secondsString = if (displaySeconds / 10 > 0) {
            Integer.toString(displaySeconds)
        } else {
            "0" + Integer.toString(displaySeconds)
        }
        return "$minutesString:$secondsString"
    }
}