/** package com.bignerdranch.android.surveyrotation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val EXTRA_DID_RESET_RESULT = "com.bignerdranch.android.surveyrotation.DID_RESET_RESULT"
class SurveyResultActivity : AppCompatActivity() {

    private lateinit var resetButton: Button
    private lateinit var continueSurveyButton: Button
    private lateinit var yesSurveyResult: TextView
    private lateinit var noSurveyResult: TextView

    private val surveyRotationViewModel: SurveyRotationViewModel by lazy {
        ViewModelProvider(this).get(SurveyRotationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_result)

        resetButton = findViewById(R.id.reset_button)
        continueSurveyButton = findViewById(R.id.continue_survey_button)
        yesSurveyResult = findViewById(R.id.yes_survey_results)
        noSurveyResult = findViewById(R.id.no_survey_results)

        val yesCount = intent.getIntExtra(EXTRA_YES_SURVEY, 0)
        val noCount = intent.getIntExtra(EXTRA_NO_SURVEY, 0)
        surveyRotationViewModel.yesAnswers = yesCount
        surveyRotationViewModel.noAnswers = noCount

        yesSurveyResult.setText(String.format("Yes count: %d", surveyRotationViewModel.yesAnswers))
        noSurveyResult.setText(String.format("No count: %d", surveyRotationViewModel.noAnswers))

        resetButton.setOnClickListener {
            resetCount()
        }

        continueSurveyButton.setOnClickListener {
            returnToSurvey()
        }
    }
    private fun resetCount() {
        surveyRotationViewModel.clearCount()
        yesSurveyResult.setText(String.format("Yes count: %d", surveyRotationViewModel.yesAnswers))
        noSurveyResult.setText(String.format("No count: %d", surveyRotationViewModel.noAnswers))
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_DID_RESET_RESULT, true)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
    private fun returnToSurvey() {
        finish()
    }
} **/