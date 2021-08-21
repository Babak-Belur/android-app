package com.babakbelur.studiary.presentation.ui.addtarget

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DatePickerDialogFragment : DialogFragment(), OnDateSetListener {

    private var dialogDateListener: OnDateSetListener? = null

    @Inject
    lateinit var calendar: Calendar

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dialogDateListener?.onDateSet(view, year, month, dayOfMonth)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)
        return DatePickerDialog(requireContext(), this, year, month, date)
    }

    fun setListeningActivity(listener: OnDateSetListener) {
        dialogDateListener = listener
    }

    companion object {
        @JvmStatic
        fun getInstance() = DatePickerDialogFragment()
    }
}