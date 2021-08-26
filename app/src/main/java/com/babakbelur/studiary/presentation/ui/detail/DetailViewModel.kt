package com.babakbelur.studiary.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.domain.usecase.IAppUseCase
import com.babakbelur.studiary.presentation.adapter.TestQuestionAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: IAppUseCase) : ViewModel() {

    private var _evalScore: MutableLiveData<Int> = MutableLiveData()
    val evalScore = _evalScore

    fun calculatePreTestScore(testAdapter: TestQuestionAdapter) {
        var score = 0
        testAdapter.onRadioButtonCheckedListener = { checkedId, position ->
            when (position) {
                0 -> if (checkedId == R.id.rb_answer_b) {
                    score += 10
                    _evalScore.value = score
                }
                1 -> if (checkedId == R.id.rb_answer_d) {
                    score += 10
                    _evalScore.value = score
                }
                2 -> if (checkedId == R.id.rb_answer_b) {
                    score += 10
                    _evalScore.value = score
                }
                3 -> if (checkedId == R.id.rb_answer_d) {
                    score += 10
                    _evalScore.value = score
                }
                4 -> if (checkedId == R.id.rb_answer_c) {
                    score += 10
                    _evalScore.value = score
                }
                5 -> if (checkedId == R.id.rb_answer_a) {
                    score += 10
                    _evalScore.value = score
                }
                6 -> if (checkedId == R.id.rb_answer_b) {
                    score += 10
                    _evalScore.value = score
                }
                7 -> if (checkedId == R.id.rb_answer_c) {
                    score += 10
                    _evalScore.value = score
                }
                8 -> if (checkedId == R.id.rb_answer_c) {
                    score += 10
                    _evalScore.value = score
                }
                9 -> if (checkedId == R.id.rb_answer_d) {
                    score += 10
                    _evalScore.value = score
                }
            }
        }
    }


}