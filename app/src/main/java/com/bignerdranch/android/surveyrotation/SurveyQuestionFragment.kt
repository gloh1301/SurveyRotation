package com.bignerdranch.android.surveyrotation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [SurveyQuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
const val EXTRA_FRAGMENT_YES_SURVEY = "com.bignerdranch.android.surveyrotation.FRAGMENT_YES_SURVEY"
const val EXTRA_FRAGMENT_NO_SURVEY = "com.bignerdranch.android.surveyrotation.FRAGMENT_NO_SURVEY"
class SurveyQuestionFragment : Fragment() {

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
        ViewModelProvider(requireActivity()).get(SurveyRotationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_survey_question, container, false)
        surveyQuestion = view.findViewById(R.id.survey_question)
        yesButton = view.findViewById(R.id.yes_button)
        noButton = view.findViewById(R.id.no_button)
        yesTotalText = view.findViewById(R.id.yes_total)
        noTotalText = view.findViewById(R.id.no_total)
        resultButton = view.findViewById(R.id.results_button)

        yesButton.setOnClickListener {
            addYesCount()
        }

        noButton.setOnClickListener {
            addNoCount()
        }

        resultButton.setOnClickListener {
            showResults()
        }
        return view
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
        val showResultsIntent = Intent(requireActivity(), SurveyResultFragment::class.java)
        showResultsIntent.putExtra(EXTRA_FRAGMENT_YES_SURVEY, surveyRotationViewModel.yesAnswers)
        showResultsIntent.putExtra(EXTRA_FRAGMENT_NO_SURVEY, surveyRotationViewModel.noAnswers)
        surveyResultLauncher.launch(showResultsIntent)
    }

    private fun handleSurveyResult(result: ActivityResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val intent = result.data
            val shouldReset = intent?.getBooleanExtra(EXTRA_FRAGMENT_DID_RESET_RESULT, false)?: false
            if (shouldReset) {
                surveyRotationViewModel.clearCount()
                yesTotalText.setText(String.format("%d", surveyRotationViewModel.yesAnswers))
                noTotalText.setText(String.format("%d", surveyRotationViewModel.noAnswers))
            }
            Toast.makeText(requireActivity(), "Results screen", Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == AppCompatActivity.RESULT_CANCELED) {
            Toast.makeText(requireActivity(), "Back to main", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() = SurveyQuestionFragment()
    }
}