package com.example.brainmath.Questions

import com.example.brainmath.Difficalty.Easy
import com.example.brainmath.Difficulty.Hard
import com.example.brainmath.Difficulty.Normal
import java.text.ParsePosition
import kotlin.random.Random

class QuestionList(private val questionType:String?) {
    // Declare variables
    private var questionList = ArrayList<Pair<String, Int>>(10)
    private var questionDataList = ArrayList<Question>(10)
    private var correctAnswer = ""

    // set Questions according to the difficulty
    private fun setQuestion(){

        when(questionType){

            "easy" -> {
                for(i in 1..10){
                    questionList.add(Easy.getQuestions())
                }
            }
            "normal" -> {
                for(i in 1..10){
                    questionList.add(Normal.getQuestions())
                }
            }
            else -> {
                for(i in 1..10){
                    questionList.add(Hard.getQuestions())
                }
            }
        }

    }

    // get option for random answers
    private fun getOption(position: Int):ArrayList<String>{

        val optionList = ArrayList<String>(4)
        var answer = questionList[position].second

        if(answer != 77777777){
            correctAnswer = answer.toString()
            optionList.add(answer.toString())
            optionList.add((answer + Random.nextInt(-9,10)).toString())
            optionList.add((answer + Random.nextInt(-9,10)).toString())
            optionList.add("NA")
        }
        else{
            correctAnswer = "NA"
            answer = Random.nextInt(1,4000)
            optionList.add((answer + Random.nextInt(-9,10)).toString())
            optionList.add((answer + Random.nextInt(-9,10)).toString())
            optionList.add((answer + Random.nextInt(-9,10)).toString())
            optionList.add("NA")
        }

        optionList.shuffle()
        return optionList
    }

    // get the question list
    fun getQuestionList():ArrayList<Question>{

        setQuestion()
        for(i in 0..9){

            val optionList = getOption(i)
            val question = Question(
                questionList[i].first,
                correctAnswer,
                optionList[0],
                optionList[1],
                optionList[2],
                optionList[3],
                "none"
            )
            questionDataList.add(question)
        }

        return questionDataList
    }

}