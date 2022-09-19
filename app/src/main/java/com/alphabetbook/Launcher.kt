package com.alphabetbook
/**
 * Splash page of the App
 * @author Thabang Thubane
 * @since 16 Sep 2022
 *
 */
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Launcher : AppCompatActivity() {
    lateinit var shared: SharedPreferences
    var last = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        supportActionBar?.hide()
        shared = getSharedPreferences("SHARED", Context.MODE_PRIVATE)
        last = shared.getBoolean("last_page", false)


        Handler().postDelayed({
            //if last is true the the app was closed in the main activity
            if(last) {
                val intent = Intent(this, MainActivity::class.java);
                startActivity(intent);
                finish()
            } else {
                val intent = Intent(this, OverView::class.java);
                startActivity(intent);
                finish()
            }
        },3000)// display the splash page for 3000 Millis

    }
}