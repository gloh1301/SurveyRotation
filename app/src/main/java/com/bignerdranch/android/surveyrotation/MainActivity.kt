package com.bignerdranch.android.surveyrotation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

const val EXTRA_SURVEY = "com.bignerdranch.android.surveyrotation.SURVEY"

class MainActivity : AppCompatActivity() {

    private lateinit var surveyQuestion: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var yesTotalText: TextView
    private lateinit var noTotalText: TextView
    private lateinit var resultButton: Button

    private val surveyResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> handleSurveyResult(result)
    }

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
        resultButton = findViewById(R.id.results_button)

        yesButton.setOnClickListener {
            addYesCount()
        }

        noButton.setOnClickListener {
            addNoCount()
        }

        resultButton.setOnClickListener {
            showResults()
        }
    }

    private fun addYesCount() {
        surveyRotationViewModel.addYes()
        yesTotalText.setText(String.format("%d", surveyRotationViewModel.yesAnswers))
    }

    private fun addNoCount() {
        surveyRotationViewModel.addNo()
        noTotalText.setText(String.format("%d", surveyRotationViewModel.noAnswers))
    }

    private fun showResults() {
        val showResultsIntent = Intent(this, SurveyResultActivity::class.java)
        surveyResultLauncher.launch(showResultsIntent)
    }

    private fun handleSurveyResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            Toast.makeText(this, "Results screen", Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Back to main", Toast.LENGTH_SHORT).show()
        }
    }
}