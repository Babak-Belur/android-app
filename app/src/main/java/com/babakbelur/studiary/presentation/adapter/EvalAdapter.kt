package com.babakbelur.studiary.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.babakbelur.studiary.core.domain.model.EvaluationItem
import com.babakbelur.studiary.databinding.ItemEvaluationBinding
import com.babakbelur.studiary.presentation.utils.toLetterDateFormat
import com.babakbelur.studiary.presentation.utils.toNumberDateFormat

class EvalAdapter: BaseAdapter<EvaluationItem, ItemEvaluationBinding>(
    ItemEvaluationBinding::inflate,
    diffCallback
) {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val evaluation = getItem(position)
        val binding = ItemEvaluationBinding.bind(holder.itemView)
        with(binding) {
            tvDate.text = evaluation.date?.toNumberDateFormat()?.toLetterDateFormat()
            tvScore.text = evaluation.grade.toString()

            when (evaluation.studyTime?.toInt()) {
                1 -> tvStudyTime.text = "<2 H"
                2 -> tvStudyTime.text = "2-5 H"
                3 -> tvStudyTime.text = "5-10 H"
                4 -> tvStudyTime.text = ">10 H"
            }

            when (evaluation.freeTime?.toInt()) {
                1 -> tvFreeTime.text = "<2 H"
                2 -> tvFreeTime.text = "2-5 H"
                3 -> tvFreeTime.text = "5-8 H"
                4 -> tvFreeTime.text = "8-15 H"
                5 -> tvFreeTime.text = ">15 H"
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<EvaluationItem>(){
            override fun areItemsTheSame(
                oldItem: EvaluationItem,
                newItem: EvaluationItem
            ): Boolean {
                return newItem.idEvaluation == oldItem.idEvaluation
            }

            override fun areContentsTheSame(
                oldItem: EvaluationItem,
                newItem: EvaluationItem
            ): Boolean {
                return newItem == oldItem
            }
        }
    }
}