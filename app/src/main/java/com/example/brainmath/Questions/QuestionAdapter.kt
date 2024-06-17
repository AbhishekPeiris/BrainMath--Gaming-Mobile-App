package com.example.brainmath.Questions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brainmath.R

class QuestionAdapter(private val dataSet:ArrayList<Question>):
RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        // Initialize a TextView variable tvProblem and assign it the TextView with id rvTvProblem from the inflated view
        val tvProblem: TextView = view.findViewById(R.id.rvTvProblem)

        // Initialize TextView variables for options 1 to 4 and assign them the respective TextViews from the inflated view
        val option1: TextView = view.findViewById(R.id.rvTvOption1)
        val option2: TextView = view.findViewById(R.id.rvTvOption2)
        val option3: TextView = view.findViewById(R.id.rvTvOption3)
        val option4: TextView = view.findViewById(R.id.rvTvOption4)

        // Initialize TextView variables for the selected answer and correct answer TextViews from the inflated view
        val selectedAnswer: TextView = view.findViewById(R.id.selectedAnswer)
        val correctAnswer: TextView = view.findViewById(R.id.correctAnswer)

    }

    // create the question
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_question_row,parent,false)
        return ViewHolder(view)
    }

    // get the item count
    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set the text of tvProblem TextView in the ViewHolder to the problem text at the current position in the dataSet
        holder.tvProblem.text = dataSet[position].problem

        // Set the text of option1 TextView in the ViewHolder to option1 text at the current position in the dataSet
        holder.option1.text = dataSet[position].option1

        // Set the text of option2 TextView in the ViewHolder to option2 text at the current position in the dataSet
        holder.option2.text = dataSet[position].option2

        // Set the text of option3 TextView in the ViewHolder to option3 text at the current position in the dataSet
        holder.option3.text = dataSet[position].option3

        // Set the text of option4 TextView in the ViewHolder to option4 text at the current position in the dataSet
        holder.option4.text = dataSet[position].option4

        // Set the text of selectedAnswer TextView in the ViewHolder to display "Your Answer: " followed by the selected option from the dataSet
        holder.selectedAnswer.text = "Your Answer: ${dataSet[position].selectedOption}"

        // Set the text of correctAnswer TextView in the ViewHolder to display "Correct Answer: " followed by the correct answer from the dataSet
        holder.correctAnswer.text = "Correct Answer: ${dataSet[position].answer}"
    }

}