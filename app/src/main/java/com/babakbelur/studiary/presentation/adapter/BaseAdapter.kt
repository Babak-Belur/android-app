package com.babakbelur.studiary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.babakbelur.studiary.presentation.base.Inflate

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val inflate: Inflate<VB>,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseAdapter.ItemViewHolder>(diffCallback) {

    class ItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    var onItemClick: ((T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(inflate.invoke(LayoutInflater.from(parent.context), parent, false))
    }
}