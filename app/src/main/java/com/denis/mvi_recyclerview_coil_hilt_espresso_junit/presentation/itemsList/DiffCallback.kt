package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.itemsList

import androidx.recyclerview.widget.DiffUtil
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.presentation.PresentationData

class DiffCallback : DiffUtil.ItemCallback<PresentationData>() {
    override fun areItemsTheSame(oldItem: PresentationData, newItem: PresentationData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PresentationData,
        newItem: PresentationData
    ): Boolean {
        return oldItem == newItem
    }
}