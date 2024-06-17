package com.example.brainmath

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.brainmath.Questions.Question
import com.example.brainmath.databinding.ActivityMainBinding

class MenuScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manu_screen)

        // Retrieve the shared preferences
        var sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // Get the username from shared preferences, defaulting to "Guest" if not found
        val userName = sharedPreferences.getString("userName", "Guest")

        // Find the TextView to display the username and set its text
        val playedisname = findViewById<TextView>(R.id.playedisname)
        playedisname.text = "Hello $userName"

        // Find the play button ImageView and set a click listener to start the game
        var MSplaybtn = findViewById<ImageView>(R.id.MSplaybtn)
        MSplaybtn.setOnClickListener {
            var intent = Intent(this,DifficaltyScreen::class.java)
            startActivity(intent)
            finish()
        }
        displayHighScore()

    }

    // display high score
    private fun displayHighScore() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val highScore = sharedPreferences.getInt("highScore", 0)

        val highScoreTextView = findViewById<TextView>(R.id.highScoreTextView)
        highScoreTextView.text = "High Score: $highScore/10"
    }
}