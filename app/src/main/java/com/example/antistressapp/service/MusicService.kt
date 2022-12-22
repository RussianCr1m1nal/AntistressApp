package com.example.antistressapp.service

import android.app.Service
import android.media.MediaPlayer
import android.content.Intent
import android.os.IBinder
import com.example.antistressapp.R

class MusicService : Service() {
    var mediaPlayer: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        (mediaPlayer as MediaPlayer).setLooping(true)
        (mediaPlayer as MediaPlayer).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun stopService(name: Intent): Boolean {
        return super.stopService(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = null
    }
}