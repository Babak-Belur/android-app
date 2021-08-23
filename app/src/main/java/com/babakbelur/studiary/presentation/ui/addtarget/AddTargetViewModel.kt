package com.babakbelur.studiary.presentation.ui.addtarget

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.babakbelur.studiary.R
import com.babakbelur.studiary.presentation.adapter.TestQuestionAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class AddTargetViewModel : ViewModel() {

    private var _preTestScore: MutableLiveData<Int> = MutableLiveData()
    val preTestScore = _preTestScore

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun calculateTotalDays(currentDay: String, selectedDay: String): Int {
        val sdf = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        val currentDate = sdf.parse(currentDay)
        val selectedDate = sdf.parse(selectedDay)
        val difference = abs(currentDate.time - selectedDate.time)
        val diffDays = difference / (24 * 60 * 60 * 1000)
        val totalDays = diffDays.toInt()
        Log.i("Test", "Total Days: $totalDays")
        return totalDays
    }

    fun calculatePreTestScore(testAdapter: TestQuestionAdapter) {
        var score = 0
        testAdapter.onRadioButtonCheckedListener = { checkedId, position ->
            when (position) {
                0 -> if (checkedId == R.id.rb_answer_b) {
                    score += 10
                    _preTestScore.value = score
                }
                1 -> if (checkedId == R.id.rb_answer_d){
                    score += 10
                    _preTestScore.value = score
                }
                2 -> if (checkedId == R.id.rb_answer_b){
                    score += 10
                    _preTestScore.value = score
                }
                3 -> if (checkedId == R.id.rb_answer_d){
                    score += 10
                    _preTestScore.value = score
                }
                4 -> if (checkedId == R.id.rb_answer_c){
                    score += 10
                    _preTestScore.value = score
                }
                5 -> if (checkedId == R.id.rb_answer_a){
                    score += 10
                    _preTestScore.value = score
                }
                6 -> if (checkedId == R.id.rb_answer_b){
                    score += 10
                    _preTestScore.value = score
                }
                7 -> if (checkedId == R.id.rb_answer_c){
                    score += 10
                    _preTestScore.value = score
                }
                8 -> if (checkedId == R.id.rb_answer_c){
                    score += 10
                    _preTestScore.value = score
                }
                9 -> if (checkedId == R.id.rb_answer_d){
                    score += 10
                    _preTestScore.value = score
                }
            }
        }
    }
}