package com.example.brainmath

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Initialize the SharedPreferences object
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Find views by their IDs
        var playername = findViewById<EditText>(R.id.playername)
        var pnokbtn = findViewById<Button>(R.id.pnokbtn)

        pnokbtn.setOnClickListener {
            val name = playername.text.toString().trim()

            if (name.isNotEmpty()) {
                saveName(name)
                val intent = Intent(this, MenuScreen::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // save user entered name
    private fun saveName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("userName", name)
        editor.apply()
    }
}