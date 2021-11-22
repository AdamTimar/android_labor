package com.example.labor5quiz.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labor5quiz.R
import com.example.labor5quiz.retrofit.models.Quiz


class QuestionsAdapter(
    private val questionList: MutableList<Quiz>,
    private val deleteButtonClickListener: DeleteButtonClickListener,
    private val detailsButtonClickListener: DetailsButtonClickListener

) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface DeleteButtonClickListener {
        fun onDeleteClick(position: Int)
    }

    interface DetailsButtonClickListener {
        fun onDetailsClick(position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val questionTextView =
            itemView.findViewById<TextView>(R.id.questionListItemQuestionTextView)
        private val questionTypeTextView =
            itemView.findViewById<TextView>(R.id.questionListItemQuestionTypeTextView)
        private val correctAnswerTextView =
            itemView.findViewById<TextView>(R.id.questionListItemCorrectAnswerTextView)

        fun bindQuestion(question: String, questionType: String, correctAnswer: String) {
            questionTextView.text = question
            questionTypeTextView.text = questionType
            correctAnswerTextView.text = correctAnswer
        }

        init{
            itemView.findViewById<TextView>(R.id.deleteTextView).setOnClickListener{
                val position = layoutPosition
                deleteButtonClickListener.onDeleteClick(position)
            }

            itemView.findViewById<TextView>(R.id.detailsTextView).setOnClickListener{
                val position = layoutPosition
                detailsButtonClickListener.onDetailsClick(position)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder

        holder = QuestionHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.question_list_item, parent, false)
        )

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentItem = questionList[position]
        if(holder is QuestionHolder){
            holder.bindQuestion(currentItem.question, currentItem.type, currentItem.correctAnswer)
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }
}
