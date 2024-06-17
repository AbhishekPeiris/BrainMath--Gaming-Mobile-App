package com.example.brainmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

// Define MainActivity as a subclass of AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Initialize a Handler using the main looper
    private val handler = Handler(Looper.getMainLooper())

    // Method called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the activity's layout
        setContentView(R.layout.activity_main)

        // Use the handler to post a delayed action after 2000 milliseconds (2 seconds)
        handler.postDelayed({
            // Create an Intent to navigate to MainActivity2
            val intent = Intent(this, MainActivity2::class.java)
            // Start the MainActivity2 and finish the current activity
            startActivity(intent)
            finish()
        }, 2000L) // Delay of 2000 milliseconds (2 seconds)
    }
}
