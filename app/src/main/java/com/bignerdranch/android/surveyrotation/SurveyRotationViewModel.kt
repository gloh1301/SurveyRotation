package com.bignerdranch.android.surveyrotation

import androidx.lifecycle.ViewModel

class SurveyRotationViewModel: ViewModel() {
    var yesAnswers = 0
    var noAnswers = 0

    fun addYes() {
        yesAnswers++
    }

    fun addNo() {
        noAnswers++
    }

    fun clearCount() {
        yesAnswers = 0
        noAnswers = 0
    }
}