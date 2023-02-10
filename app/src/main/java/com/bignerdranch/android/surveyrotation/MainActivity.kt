package com.bignerdranch.android.surveyrotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var surveyQuestion: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var yesTotalText: TextView
    private lateinit var noTotalText: TextView
    private lateinit var resetButton: Button

    private val surveyRotationViewModel: SurveyRotationViewModel by lazy {
        ViewModelProvider(this).get(SurveyRotationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surveyQuestion = findViewById(R.id.survey_question)
        yesButton = findViewById(R.id.yes_button)
        noButton = findViewById(R.id.no_button)
        yesTotalText = findViewById(R.id.yes_total)
        noTotalText = findViewById(R.id.no_total)
        resetButton = findViewById(R.id.reset_button)

        yesButton.setOnClickListener {
            addYesCount()
        }

        noButton.setOnClickListener {
            addNoCount()
        }

        resetButton.setOnClickListener {
            resetCount()
        }
    }

    private fun addYesCount() {
        var yesAnswers = 0
        yesAnswers++
        yesTotalText.setText(String.format("%d", yesAnswers))
        surveyRotationViewModel.addYes()
    }

    private fun addNoCount() {
        var noAnswers = 0
        noAnswers++
        noTotalText.setText(String.format("%d", noAnswers))
        surveyRotationViewModel.addNo()
    }

    private fun resetCount() {
        yesTotalText.text = ""
        noTotalText.text = ""
        surveyRotationViewModel.clearCount()
    }
}