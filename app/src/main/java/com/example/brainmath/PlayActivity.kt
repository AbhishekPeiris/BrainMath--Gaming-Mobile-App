package com.example.brainmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.brainmath.Questions.Question
import com.example.brainmath.Questions.QuestionList
import com.example.brainmath.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    private var binding:ActivityPlayBinding? = null

    // Variables to track game progress and score
    private var position = 0
    private var timer: CountDownTimer? = null
    private var timeGiven = 0
    private var scores = 0
    private var questionDataList = ArrayList<Question>(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // navigate the play game according to the selected question type(difficulty)
        val questionType = intent.getStringExtra("questionType")
        questionDataList = QuestionList(questionType).getQuestionList()

        setGivenTime(questionType)

        updateQuestion()
        updateOption()
        updateHorizontalProgressBar()
        startTimer()

        binding?.btnption1?.setOnClickListener {
            onSelectOption(binding?.btnption1?.text.toString())
        }
        binding?.btnption2?.setOnClickListener {
            onSelectOption(binding?.btnption2?.text.toString())
        }
        binding?.btnption3?.setOnClickListener {
            onSelectOption(binding?.btnption3?.text.toString())
        }
        binding?.btnption4?.setOnClickListener {
            onSelectOption(binding?.btnption4?.text.toString())
        }
    }

    // back press
    override fun onBackPressed(){
        super.onBackPressed()
        endGame()
    }

    // update question
    private fun updateQuestion(){
        binding?.tvQuestion?.text = questionDataList[position].problem
    }

    // update option
    private fun updateOption(){
        binding?.btnption1?.text = questionDataList[position].option1
        binding?.btnption2?.text = questionDataList[position].option2
        binding?.btnption3?.text = questionDataList[position].option3
        binding?.btnption4?.text = questionDataList[position].option4
    }

    // update horizontal progress bar
    private fun updateHorizontalProgressBar(){
        binding?.horizontalProgressBar?.incrementProgressBy(1)
    }

    // set time
    private fun setGivenTime(level:String?){
        timeGiven = when(level){
            "easy" -> 10000
            "normal" -> 12000
            else -> 15000
        }
    }

    // time start
    private fun startTimer(){
        var count = timeGiven/1000
        binding?.circularProgressBar?.progress = timeGiven/1000
        binding?.circularProgressBar?.max = timeGiven/1000

        timer = object : CountDownTimer(timeGiven.toLong(), 1000){

            override fun onTick(p0:Long){
                binding?.circularProgressBar?.incrementProgressBy(-1)
                count--
                binding?.tvCountDown?.text = count.toString()
            }


            override fun onFinish() {
                setNextRound()
            }
        }.start()
    }

    // select option
    private fun onSelectOption(option:String){

        if(option == questionDataList[position].answer)
            scores++
        questionDataList[position].selectedOption = option
        setNextRound()
    }

    // set next round
    private fun setNextRound(){

        if(position < 9){
            position++
            timer?.cancel()
            timer = null
            updateHorizontalProgressBar()
            updateQuestion()
            updateOption()
            startTimer()
        }
        else{
            endGame()
        }
    }

    // end game
    private fun endGame(){

        val intent = Intent(this,FinishActivity::class.java)
        intent.putExtra("score",scores)
        intent.putExtra("dataSet",questionDataList)
        startActivity(intent)
        finish()
        timer?.cancel()
        timer = null
        binding = null
    }
}