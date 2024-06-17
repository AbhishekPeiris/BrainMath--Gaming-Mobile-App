package com.example.brainmath

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainmath.Questions.Question
import com.example.brainmath.Questions.QuestionAdapter

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        // Get the score and data from the intent
        val score = intent.getIntExtra("score", 0)
        val data: ArrayList<Question> = intent.getSerializableExtra("dataSet") as ArrayList<Question>

        // display the score
        val tvScore: TextView = findViewById(R.id.tvScore)
        tvScore.text = "Your Score\n$score/10"

        setAdapterRecyclerView(data)

        // navigate the home
        val btnHome: Button = findViewById(R.id.btnHome)
        btnHome.setOnClickListener { finish() }

        updateHighScore(score)


    }

    private fun setAdapterRecyclerView(data:ArrayList<Question>){
        val recyclerView:RecyclerView = findViewById(R.id.rvQuestionList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = QuestionAdapter(data)
        recyclerView.adapter = adapter
    }

    // update the high score
    private fun updateHighScore(score: Int) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val highScore = sharedPreferences.getInt("highScore", 0)
        if (score > highScore) {
            editor.putInt("highScore", score)
            editor.apply()
        }
    }
}