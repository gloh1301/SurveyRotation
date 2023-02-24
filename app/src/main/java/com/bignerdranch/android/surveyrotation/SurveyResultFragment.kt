package com.bignerdranch.android.surveyrotation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [SurveyResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
const val EXTRA_FRAGMENT_DID_RESET_RESULT = "com.bignerdranch.android.surveyrotation.FRAGMENT_DID_RESET_RESULT"
class SurveyResultFragment : Fragment() {

    private lateinit var resetButton: Button
    private lateinit var continueSurveyButton: Button
    private lateinit var yesSurveyResult: TextView
    private lateinit var noSurveyResult: TextView

    private val surveyRotationViewModel: SurveyRotationViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SurveyRotationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_survey_result, container, false)
        resetButton = view.findViewById(R.id.reset_button)
        continueSurveyButton = view.findViewById(R.id.continue_survey_button)
        yesSurveyResult = view.findViewById(R.id.yes_survey_results)
        noSurveyResult = view.findViewById(R.id.no_survey_results)

        val intent = Intent (activity, AppCompatActivity::class.java)
        activity?.startActivity(intent)
        val yesCount = intent.getIntExtra(EXTRA_FRAGMENT_YES_SURVEY, 0)
        val noCount = intent.getIntExtra(EXTRA_FRAGMENT_NO_SURVEY, 0)
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
        return view
    }
    private fun resetCount() {
        surveyRotationViewModel.clearCount()
        yesSurveyResult.setText(String.format("Yes count: %d", surveyRotationViewModel.yesAnswers))
        noSurveyResult.setText(String.format("No count: %d", surveyRotationViewModel.noAnswers))
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_FRAGMENT_DID_RESET_RESULT, true)
        getActivity()?.setResult(AppCompatActivity.RESULT_OK, resultIntent)
        // finish()
    }
    private fun returnToSurvey() {
        // finish()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() = SurveyResultFragment()
    }
}