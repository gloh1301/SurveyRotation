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

// const val EXTRA_YES_SURVEY = "com.bignerdranch.android.surveyrotation.YES_SURVEY"
// const val EXTRA_NO_SURVEY = "com.bignerdranch.android.surveyrotation.NO_SURVEY"

class MainActivity : AppCompatActivity() {

    /** private lateinit var surveyQuestion: TextView
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
    } **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.setFragmentResultListener(EXTRA_FRAGMENT_DID_RESET_RESULT, this) {
                requestKey, bundle ->
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SurveyResultFragment.newInstance())
                .addToBackStack("SURVEY_RESULT")
                .commit()
        }

        /** surveyQuestion = findViewById(R.id.survey_question)
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
        showResultsIntent.putExtra(EXTRA_YES_SURVEY, surveyRotationViewModel.yesAnswers)
        showResultsIntent.putExtra(EXTRA_NO_SURVEY, surveyRotationViewModel.noAnswers)
        surveyResultLauncher.launch(showResultsIntent)
    }

    private fun handleSurveyResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val shouldReset = intent?.getBooleanExtra(EXTRA_DID_RESET_RESULT, false)?: false
            if (shouldReset) {
                surveyRotationViewModel.clearCount()
                yesTotalText.setText(String.format("%d", surveyRotationViewModel.yesAnswers))
                noTotalText.setText(String.format("%d", surveyRotationViewModel.noAnswers))
            }
            Toast.makeText(this, "Results screen", Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Back to main", Toast.LENGTH_SHORT).show()
        } **/
    }
}