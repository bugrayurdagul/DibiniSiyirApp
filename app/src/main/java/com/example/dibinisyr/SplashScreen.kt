package com.example.dibinisyr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.dibinisyr.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var tasarim:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        val topAnimation = AnimationUtils.loadAnimation(this,R.anim.from_left)
        val bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.from_right)

        tasarim.ivLogo.startAnimation(topAnimation)
        tasarim.textView.startAnimation(bottomAnimation)

        val splashScreenTimeout = 3000
        val homeIntent = Intent(this@SplashScreen,MainActivity::class.java)

        Handler().postDelayed({
            startActivity((homeIntent))
            finish()
        },splashScreenTimeout.toLong())



    }
}