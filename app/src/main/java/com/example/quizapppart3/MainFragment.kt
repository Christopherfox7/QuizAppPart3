package com.example.quizapppart3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.quizapppart3.databinding.FragmentMainBinding

const val KEY_QUESTION_INDEX = "question_index"

class MainFragment : Fragment() {

    var questionIndex = 0
    data class Question(val questionID: Int,  val questionAnswer: Boolean)
    val questions= listOf(Question(R.string.question1, true),Question(R.string.question2, false), Question(R.string.question3, true),Question(R.string.question4, true),Question(R.string.question5, false))

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        if(savedInstanceState != null){
            questionIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX)
        }

        updateQuestionText()
        binding.TrueButton.setOnClickListener{
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            nextQuestion()
        }
        binding.backButton.setOnClickListener {
            backQuestion()
        }
        binding.questionText.setOnClickListener {
            nextQuestion()
        }
        return rootView
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(KEY_QUESTION_INDEX, questionIndex)
    }
    fun nextQuestion(){
        questionIndex = questionIndex +1

        if(questionIndex.equals(questions.size)){
            questionIndex=0
            updateQuestionText()
        }
        updateQuestionText()
    }
    fun backQuestion(){
        if(questionIndex.equals(0)){
            questionIndex=questions.size-1
            updateQuestionText()
        }
        else {
            questionIndex = questionIndex - 1
            updateQuestionText()
        }
    }
    fun updateQuestionText(){
        binding.questionText.text = getString(questions.get(questionIndex).questionID)
    }
    fun checkAnswer( answer: Boolean){

        if(questions.get(questionIndex).questionAnswer == answer){
            Toast.makeText(activity, R.string.correctToast, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity, R.string.wrongToast, Toast.LENGTH_SHORT).show()
        }

    }




    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}