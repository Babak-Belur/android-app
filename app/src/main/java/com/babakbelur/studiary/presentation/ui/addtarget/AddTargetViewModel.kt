package com.babakbelur.studiary.presentation.ui.addtarget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.babakbelur.studiary.R
import com.babakbelur.studiary.core.domain.usecase.IAppUseCase
import com.babakbelur.studiary.presentation.adapter.TestQuestionAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTargetViewModel @Inject constructor(private val useCase: IAppUseCase) : ViewModel() {

    private var _preTestScore: MutableLiveData<Int> = MutableLiveData()
    val preTestScore = _preTestScore

    val addTarget = useCase.addTarget.asLiveData(viewModelScope.coroutineContext)

    val addCourse = useCase.addCourse.asLiveData(viewModelScope.coroutineContext)

    val listCourses = useCase.listCourses.asLiveData(viewModelScope.coroutineContext)

    fun getAllCourses() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getAllCourses()
    }

    fun addCourse(subject: String, description: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.addCourse(subject, description)
    }

    fun addTarget(
        userId: Int,
        courseId: Int,
        preTestScore: Int,
        targetScore: Int,
        targetTime: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        useCase.addTarget(userId, courseId, preTestScore, targetScore, targetTime)
    }

    fun calculatePreTestScore(testAdapter: TestQuestionAdapter) {
        var score = 0
        testAdapter.onRadioButtonCheckedListener = { checkedId, position ->
            when (position) {
                0 -> if (checkedId == R.id.rb_answer_b) {
                    score += 10
                    _preTestScore.value = score
                }
                1 -> if (checkedId == R.id.rb_answer_d) {
                    score += 10
                    _preTestScore.value = score
                }
                2 -> if (checkedId == R.id.rb_answer_b) {
                    score += 10
                    _preTestScore.value = score
                }
                3 -> if (checkedId == R.id.rb_answer_d) {
                    score += 10
                    _preTestScore.value = score
                }
                4 -> if (checkedId == R.id.rb_answer_c) {
                    score += 10
                    _preTestScore.value = score
                }
                5 -> if (checkedId == R.id.rb_answer_a) {
                    score += 10
                    _preTestScore.value = score
                }
                6 -> if (checkedId == R.id.rb_answer_b) {
                    score += 10
                    _preTestScore.value = score
                }
                7 -> if (checkedId == R.id.rb_answer_c) {
                    score += 10
                    _preTestScore.value = score
                }
                8 -> if (checkedId == R.id.rb_answer_c) {
                    score += 10
                    _preTestScore.value = score
                }
                9 -> if (checkedId == R.id.rb_answer_d) {
                    score += 10
                    _preTestScore.value = score
                }
            }
        }
    }
}