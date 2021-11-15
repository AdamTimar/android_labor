package com.example.labor5quiz.fragments

import android.R.attr
import android.annotation.SuppressLint
import com.example.labor5quiz.utils.Quiz
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labor5quiz.MainActivity
import com.example.labor5quiz.adapters.QuestionsAdapter
import com.example.labor5quiz.R
import android.R.attr.fragment

import android.R.attr.key
import com.example.labor5quiz.Constants


class QuestionListFragment : Fragment(), QuestionsAdapter.DeleteButtonClickListener, QuestionsAdapter.DetailsButtonClickListener {
    private lateinit var questionsRecyclerView : RecyclerView
    private lateinit var noQuizTextView : TextView
    private lateinit var questionAdapter: QuestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_question_list, container, false)

        questionsRecyclerView = rootView.findViewById(R.id.questionsRecyclerView)

        noQuizTextView = rootView.findViewById(R.id.noQuizTextView)

        questionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val quizzes = ((activity as MainActivity).getMainActivityViewModel().questions as MutableList<Quiz>)

        questionAdapter = QuestionsAdapter(quizzes,this,this)

        questionsRecyclerView.adapter = questionAdapter

        if(questionAdapter.itemCount == 0){
            questionsRecyclerView.visibility = View.GONE
            noQuizTextView.visibility = View.VISIBLE
        }

        else{
            questionsRecyclerView.visibility = View.VISIBLE
            noQuizTextView.visibility = View.GONE
        }


        questionAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }

            fun checkEmpty() {
                if(questionAdapter.itemCount == 0){
                    questionsRecyclerView.visibility = View.GONE
                    noQuizTextView.visibility = View.VISIBLE
                }

                else{
                    questionsRecyclerView.visibility = View.VISIBLE
                    noQuizTextView.visibility = View.GONE
                }
            }
        })


        return rootView
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDeleteClick(position: Int) {
        ((activity as MainActivity).getMainActivityViewModel().questions as MutableList<Quiz>).removeAt(position)
        questionAdapter.notifyDataSetChanged()
    }

    override fun onDetailsClick(position: Int) {
        val manager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val questionDetailFragment = QuestionDetailFragment()
        val question = ((activity as MainActivity).getMainActivityViewModel().questions as MutableList<Quiz>)[position]
        val bundle = Bundle()
        bundle.putString(Constants.DETAILSQUESTION, question.text)
        bundle.putString(Constants.QUESTIONTYPE, "Single answer")
        bundle.putString(Constants.CORRECT_ANSWER, question.goodAnswer)
        bundle.putString(Constants.ANSWER1, question.answers[0])
        bundle.putString(Constants.ANSWER2, question.answers[1])
        bundle.putString(Constants.ANSWER3, question.answers[2])
        bundle.putString(Constants.ANSWER4, question.answers[3])
        questionDetailFragment.arguments = bundle
        transaction.replace(R.id.fragmentContainerView, questionDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}