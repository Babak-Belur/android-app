package com.babakbelur.studiary.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.babakbelur.studiary.core.domain.model.TestQuestion
import com.babakbelur.studiary.databinding.ItemQuestionBinding

class TestQuestionAdapter :
    BaseAdapter<TestQuestion, ItemQuestionBinding>(ItemQuestionBinding::inflate, diffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dataItem = getItem(position)
        val binding = ItemQuestionBinding.bind(holder.itemView)
        with(binding) {
            tvQuestion.text = dataItem.question
            rbAnswerA.text = dataItem.answerA
            rbAnswerB.text = dataItem.answerB
            rbAnswerC.text = dataItem.answerC
            rbAnswerD.text = dataItem.answerD
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TestQuestion>() {
            override fun areItemsTheSame(oldItem: TestQuestion, newItem: TestQuestion): Boolean {
                return newItem.question == oldItem.question
            }

            override fun areContentsTheSame(oldItem: TestQuestion, newItem: TestQuestion): Boolean {
                return newItem == oldItem
            }
        }
    }
}