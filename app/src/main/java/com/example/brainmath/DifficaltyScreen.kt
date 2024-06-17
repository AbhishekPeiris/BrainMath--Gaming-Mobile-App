package com.example.brainmath

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.brainmath.databinding.ActivityDifficaltyScreenBinding
import com.example.brainmath.databinding.ActivityMainBinding

// Define DifficultyScreen as a subclass of AppCompatActivity
class DifficaltyScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    // Binding object for the activity's layout
    private var binding:ActivityDifficaltyScreenBinding? = null

    // Method called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using the binding object
        binding = ActivityDifficaltyScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Set click listeners for the difficulty buttons
        binding?.DSeasybtn?.setOnClickListener { startGame("easy") }
        binding?.DSnmlbtn?.setOnClickListener { startGame("normal") }
        binding?.DShardbtn?.setOnClickListener { startGame("hard") }


        // Set click listener for the cancel button
        var DfScancelbtn = findViewById<ImageView>(R.id.DfScancelbtn)
        DfScancelbtn.setOnClickListener {
            var intent = Intent(this,MenuScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    // start the game
    private fun startGame(questionType:String){
        val intent = Intent(this,PlayActivity::class.java)
        intent.putExtra("questionType",questionType)
        startActivity(intent)
    }
}